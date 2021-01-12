package com.suyh;

import com.suyh.utils.JsonUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//        2
//        3 3
//        1 4 7
//        3 6 7
//        1
//        6
//
//
//        2

public class Work {

    /**
     * 2021-01-12 工作级考试
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入地铁环线数量，范围[1, 500]: ");
        // 地址环线数量
        int lineCount = 0;
        if (sc.hasNext()) {
            lineCount = sc.nextInt();
        }
        // 所有的地铁环线
        SubwayCircleLine[] subwayCircleLines = new SubwayCircleLine[lineCount];
        System.out.print("请输入每条地铁环线站点数量的数组，取值范围[1, 10^5]: ");
        inputSiteCount(sc, subwayCircleLines);
        for (SubwayCircleLine subwayCircleLine : subwayCircleLines) {
            SubwaySite[] sites = subwayCircleLine.getSites();
            System.out.print("请输入地铁环线[" + subwayCircleLine.getCodeCircleLine() + "]的站点编号，取值范围[0, 10^6): ");
            inputSiteCode(sc, sites);
        }

        System.out.println("==========================");
        for (SubwayCircleLine subwayCircleLine : subwayCircleLines) {
            String jsonValue = JsonUtil.serializable(subwayCircleLine);
            System.out.println("subwayCircleLine: " + jsonValue);
        }

        // <站点编号, 站点所属环线>
        Map<Integer, List<SubwayCircleLine>> siteLineMapping = mappingSiteLine(subwayCircleLines);
        System.out.println("################ 站点-环线映射关系 #################");
        siteLineMapping.forEach((site, lines) -> {
            System.out.println("当前站点[" + site + "], 所在环线: " + lines);
        });

        System.out.print("请输入始发站编号: ");
        Integer sourceSiteCode = sc.nextInt();
        System.out.print("请输入终点站编号: ");
        Integer destSiteCode = sc.nextInt();
        // 始发站所在环线的所有编号
        List<SubwayCircleLine> sourceCircleLines = siteLineMapping.get(sourceSiteCode);
        // 终点站所在环线的所有编号
        List<SubwayCircleLine> destCircleLines = siteLineMapping.get(sourceSiteCode);
        int minNumber = 0;
        for (SubwayCircleLine curCircleLine : sourceCircleLines) {
            Integer currentNumber = 0;
            if (curCircleLine.exist(destSiteCode)) {
                // 最短为1，直接返回结果
                minNumber = 1;
                break;
            }

            ResultValue resultValue = new ResultValue();
            resultValue.setSuccess(false);
            resultValue.setNum(1);

            SubwaySite[] sites = curCircleLine.getSites();
            for (SubwaySite site : sites) {
                if (site.getSiteCode().equals(sourceSiteCode)) {
                    continue;
                }
                arriveSite(siteLineMapping, curCircleLine.getCodeCircleLine(), site, destSiteCode,
                        resultValue);
                if (resultValue.isSuccess()) {
                    if (resultValue.getNum() < minNumber) {
                        minNumber = resultValue.getNum();
                    }
                }
            }
        }

        System.out.println(minNumber);
        //        int n = 3;
        //        Integer[] site = new Integer[n];
    }

    public static class ResultValue {
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        private boolean success;
        private int num;
    }

    /**
     * 递归调用，找到目标站点
     * 从当前站点到达目标站点
     *
     * @param totalNumber
     * @return
     */
    private static void arriveSite(Map<Integer, List<SubwayCircleLine>> siteLineMapping, Integer excludeCircleLine,
                                   SubwaySite curSiteCode, Integer destSiteCode, ResultValue resultValue) {
        if (curSiteCode == null) {
            resultValue.setSuccess(false);
            return;
        }

        resultValue.setNum(resultValue.getNum() + 1);

        List<SubwayCircleLine> subwayCircleLines = siteLineMapping.get(curSiteCode.getSiteCode());
        if (subwayCircleLines == null || subwayCircleLines.isEmpty()) {
            resultValue.setSuccess(false);
            return;
        }
        for (SubwayCircleLine curCircleLine : subwayCircleLines) {
            if (curCircleLine.getCodeCircleLine().equals(excludeCircleLine)) {
                // 排除的环线，已经处理过了的环线
                continue;
            }

            if (curCircleLine.exist(destSiteCode)) {
                resultValue.setSuccess(true);
                return;
            }

            SubwaySite[] sites = curCircleLine.getSites();
            for (SubwaySite site : sites) {
                arriveSite(siteLineMapping, curCircleLine.getCodeCircleLine(), site, destSiteCode,
                        resultValue);
                if (resultValue.isSuccess()) {
                    resultValue.setSuccess(true);
                    return;
                }
            }
        }

        resultValue.setSuccess(false);
        return;
    }

    /**
     * 添加站点与环线之间的映射关系
     *
     * @param subwayCircleLines
     * @return
     */
    private static Map<Integer, List<SubwayCircleLine>> mappingSiteLine(SubwayCircleLine[] subwayCircleLines) {
        Map<Integer, List<SubwayCircleLine>> siteLineMapping = new HashMap<>();
        for (SubwayCircleLine subwayCircleLine : subwayCircleLines) {
            // 当前环线编号
            SubwaySite[] sites = subwayCircleLine.getSites();
            for (SubwaySite site : sites) {
                List<SubwayCircleLine> circleCollection = siteLineMapping.get(site.getSiteCode());
                if (circleCollection == null) {
                    circleCollection = new ArrayList<>();
                }
                circleCollection.add(subwayCircleLine);
                // 当前站点，与所在环线的映射关系
                siteLineMapping.put(site.getSiteCode(), circleCollection);
            }
        }
        return siteLineMapping;
    }

    /**
     * 收集每个站点输入站点编号
     *
     * @param sc
     * @param sites
     */
    private static void inputSiteCode(Scanner sc, SubwaySite[] sites) {
        for (int i = 0; i < sites.length; i++) {
            SubwaySite site = new SubwaySite();
            sites[i] = site;

            // System.out.print("请输入地铁环线[" + circleLineCode + "]的站点编号，取值范围[0, 10^6): ");
            int codeForSite = sc.nextInt();
            site.setSiteCode(codeForSite);
        }
    }

    /**
     * 输入站点数量
     *
     * @param sc
     * @param subwayCircleLines 环线数组
     */
    private static void inputSiteCount(Scanner sc, SubwayCircleLine[] subwayCircleLines) {
        for (int i = 0; i < subwayCircleLines.length; i++) {
            // System.out.print("请输入每条地铁环线站点数量的数组，取值范围[1, 10^5]: ");
            int siteCountPerLine = sc.nextInt();

            SubwayCircleLine subwayCircleLine = new SubwayCircleLine();
            // 环线编号
            subwayCircleLine.setCodeCircleLine(i + 1);
            subwayCircleLine.sites = new SubwaySite[siteCountPerLine];
            subwayCircleLines[i] = subwayCircleLine;
        }
    }

    /**
     * 地铁环线
     */
    @Data
    public static class SubwayCircleLine {
        // 当前环线编号
        private Integer codeCircleLine = null;

        // 当前环线的所有站点
        private SubwaySite[] sites = null;

        public Integer getCodeCircleLine() {
            return codeCircleLine;
        }

        public void setCodeCircleLine(Integer codeCircleLine) {
            this.codeCircleLine = codeCircleLine;
        }

        public SubwaySite[] getSites() {
            return sites;
        }

        public void setSites(SubwaySite[] sites) {
            this.sites = sites;
        }

        /**
         * 当前环线上面是否存在指定站点
         *
         * @param destSiteCode
         * @return
         */
        public boolean exist(Integer destSiteCode) {
            for (SubwaySite site : sites) {
                if (site.getSiteCode().equals(destSiteCode)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 环线站点
     */
    @Data
    public static class SubwaySite {
        // 当前站点编号
        private Integer siteCode;

        public Integer getSiteCode() {
            return siteCode;
        }

        public void setSiteCode(Integer siteCode) {
            this.siteCode = siteCode;
        }
    }
}