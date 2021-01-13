package com.suyh;

import com.suyh.utils.JsonUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 3
 * 4 4 3
 * 1 3 4 7
 * 3 9 6 7
 * 3 2 6
 * 1
 * 2
 *
 * 结果: 2
 */

/**
 * 题目描述
 * 某个城市规划了多条地铁环线，每条线路routes[i]上都有一列地铁在上面循环行驶，并且每条环线的站点数量可以不同。比如，现在存在一条线路routes[0] = [1, 4, 6, 7]，表示第一列地铁会一直按照1->4->6->7->1->4->6->7->…的站台线路环绕行驶。
 * 假设乘客小明刚开始不在任何一列地铁车厢内，他想从某一始发站S出发到达终点站T，中间只能换乘该城市规划的地铁环线，求出小明最少乘坐的地铁数量。如果不能到达终点站，那么应该返回-1表示目的地不可达。
 *
 *
 *
 *
 * 输入
 * 输入的第一行表示地铁环线数量N，取值范围[1, 500]
 * 输入的第二行表示每条地铁环线站点数量的数组，取值范围[1, 10^5]
 * 输入的第三行到第N+2行表示每条地铁环线的站点编号，取值范围[0, 10^6)
 * 输入的倒数第二行表示始发站编号S
 * 输入的最后一行表示终点站标号T
 *
 * 输出
 * 最少乘坐的地铁数量
 *
 * 样例
 * 输入样例 1 复制
 *
 * 2
 * 3 3
 * 1 4 7
 * 3 6 7
 * 1
 * 6
 * 输出样例 1
 *
 * 2
 * 提示
 *
 *
 * 输入样例 2 复制
 *
 * 3
 * 2 2 2
 * 11 12
 * 12 13
 * 13 14
 * 11
 * 14
 * 输出样例 2
 *
 * 3
 */
public class Work {

    private static Map<Integer, List<SubwayCircleLine>> siteLineMapping = null;

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
        siteLineMapping = mappingSiteLine(subwayCircleLines);
        System.out.println("################ 站点-环线映射关系 #################");
        siteLineMapping.forEach((site, lines) -> {
            System.out.println("当前站点[" + site + "], 所在环线: " + lines);
        });

        System.out.print("请输入始发站编号: ");
        Integer sourceSiteCode = sc.nextInt();
        System.out.print("请输入终点站编号: ");
        Integer destSiteCode = sc.nextInt();
        // int minNumber = doMinNumber(sourceSiteCode, destSiteCode);
        int minNumber = doMinNumber02(sourceSiteCode, destSiteCode);

        System.out.println("最后的结果: " + minNumber);
    }

    private static int doMinNumber02(Integer sourceSiteCode, Integer destSiteCode) {
        // 当前就是第一条环线
        int resultNumber = 1;
        // 始发站所在环线的所有编号
        List<SubwayCircleLine> sourceCircleLines = siteLineMapping.get(sourceSiteCode);
        if (sourceCircleLines 中存在 destSiteCode) {
            return resultNumber;
        }

        // 已处理过的所有的站点
        Set<Integer> excludeSites = new HashSet<>();
        excludeSites.add(sourceSiteCode);

        // 已处理过的所有的环线
        Set<Integer> excludeLines = new HashSet<>();
        excludeLines.addAll(sourceCircleLines.iterator());

        // 这些线上的所有站点
        Set<Integer> sites = sourceCircleLines.getAllSize();
        // 这些线上的所有站点所在的所有的线
        Set<Integer> lines = siteLineMapping.get(sites 中的每一个站点);

        // 最新的要处理的环线
        Set<Integer> newLines = lines.exclude(excludeLines);

        return doing(newLines, destSiteCode, resultNumber, excludeSites, excludeLines);
    }

    /**
     *
     *
     * @param lines 当前要处理的环线集合
     * @param destSiteCode 目标站点
     * @param number 当前的最小环线数量
     * @param excludeSites 已处理过的站点
     * @param excludeLines 已处理过的环线
     */
    private static int doing(Set<Integer> lines, Integer destSiteCode, int number,
                              Set<Integer> excludeSites, Set<Integer> excludeLines) {
        ++number;
        if (lines exist destSiteCode) {
            // 找到了最小环线数量
            return number;
        }

        // 1. 将lines 中每条环线的每个站点都添加到 excludeSites，表示这些站点都处理过了。就是现在处理的。

        // 2. 将lines 中的每条环线都添加到 excludeLines，表示这些环线都处理过了。就是现在处理的。

        // 3. lines 中的所有站点
        Set<Integer> allSites;

        // 4. allSites 中排除掉 excludeSites 已处理过的站点，剩下的站点集合
        Set<Integer> curSites;

        // 5. curSites 中的所有的站点，不过要排除掉 excludeLines中的所有环线。剩下的环线就是接下来要处理的环线
        Set<Integer> nextLines;

        return doing(nextLines, destSiteCode, number, excludeSites, excludeLines);

    }

    /**
     * 求最小路径环线数
     *
     * @param sourceSiteCode 始发站点
     * @param destSiteCode 目标站点
     * @return 最小路径环线
     */
    private static int doMinNumber(Integer sourceSiteCode, Integer destSiteCode) {
        // 始发站所在环线的所有编号
        List<SubwayCircleLine> sourceCircleLines = siteLineMapping.get(sourceSiteCode);
        int minNumber = Integer.MAX_VALUE;
        for (SubwayCircleLine curCircleLine : sourceCircleLines) {
            if (curCircleLine.exist(destSiteCode)) {
                // 最短为1，直接返回结果
                minNumber = 1;
                break;
            }

            Integer curCodeCircleLine = curCircleLine.getCodeCircleLine();
            SubwaySite[] sites = curCircleLine.getSites();
            for (SubwaySite site : sites) {
                if (site.getSiteCode().equals(sourceSiteCode)) {
                    continue;
                }
                ResultValue resultValue = new ResultValue();
                resultValue.addCircleLine(curCodeCircleLine);
                resultValue.add(1);
                ResultValue curResult = arriveSite(site.getSiteCode(), destSiteCode, resultValue);
                if (curResult.isSuccess()) {
                    if (curResult.getNum() < minNumber) {
                        minNumber = curResult.getNum();
                    }
                }
            }
        }
        return minNumber;
    }

    public static class ResultValue {
        private boolean success = false;
        private int num = 0;

        // 一共所经历过的所有环线
        private Set<Integer> circleLines = new HashSet<>();

        public void add(int value) {
            num += value;
        }

        public void addCircleLine(Integer circleLineCode) {
            circleLines.add(circleLineCode);
        }

        public boolean existCircleLine(Integer circleLineCode) {
            return circleLines.contains(circleLineCode);
        }

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

        public Set<Integer> getCircleLines() {
            return circleLines;
        }

        public void addCircleLines(Set<Integer> circleLines) {
            this.circleLines.addAll(circleLines);
        }
    }

    /**
     * 从指定站点(${siteCode}) 走到目标站点(${destSiteCode})
     *
     * @param siteCode 所在环线的指定站点
     * @param destSiteCode 目标站点
     * @param resultValue 当前走过的环线
     * @return
     */
    private static ResultValue arriveSite(Integer siteCode, Integer destSiteCode, final ResultValue resultValue) {
        resultValue.setSuccess(false);
        resultValue.add(1);

        ResultValue finalResult = new ResultValue();
        finalResult.setNum(resultValue.getNum());
        finalResult.addCircleLines(resultValue.getCircleLines());

        List<SubwayCircleLine> subwayCircleLines = siteLineMapping.get(siteCode);
        if (subwayCircleLines == null || subwayCircleLines.isEmpty()) {
            finalResult.setSuccess(false);
            return finalResult;
        }

        // 这里的数量超过一个，那么当前站点，就是换乘站
        for (SubwayCircleLine curCircleLine : subwayCircleLines) {
            // 已经跑过的环线，不能再跑
            if (resultValue.existCircleLine(curCircleLine.getCodeCircleLine())) {
                continue;
            }

            ResultValue middleResult = new ResultValue();
            middleResult.setNum(resultValue.getNum());
            middleResult.addCircleLines(resultValue.getCircleLines());
            middleResult.addCircleLine(curCircleLine.getCodeCircleLine());

            // 这个站点的其它环线是否包含了目标站点
            if (curCircleLine.exist(destSiteCode)) {
                System.out.println("找到了一条线路");
                finalResult.setSuccess(true);
                finalResult.addCircleLines(middleResult.getCircleLines());
                finalResult.setNum(middleResult.getNum());
                return finalResult;
                // continue;   // 继续，是寻找所有的可能线程。这里可以直接返回的
            }

            SubwaySite[] sites = curCircleLine.getSites();
            for (SubwaySite site : sites) {
                // 已经处理过的站点，排除掉
                if (site.getSiteCode().equals(siteCode)) {
                    continue;
                }
                ResultValue nextResult = new ResultValue();
                nextResult.setNum(middleResult.getNum());
                nextResult.addCircleLines(middleResult.getCircleLines());
                ResultValue tempResultValue = arriveSite(site.getSiteCode(), destSiteCode, nextResult);
                if (tempResultValue.isSuccess()) {
                    if (tempResultValue.getNum() < finalResult.getNum()) {
                        finalResult.setSuccess(true);
                        finalResult.setNum(tempResultValue.getNum());
                        finalResult.addCircleLines(tempResultValue.getCircleLines());

                        // 这里的确是完全可以return了，因为只要每多递归进入一次，线程就会+1，只会更大，不会更小。
                        return finalResult;
                    }
                }
            }
        }

        return finalResult;
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
