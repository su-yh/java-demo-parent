public class LogicBit {
    private final boolean isLogicAnd;
    private boolean isInit = false;
    private long flagValue;

    public LogicBit(boolean isLogicAnd) {
        this.isLogicAnd = isLogicAnd;
    }

    /**
     * 是否为无效的二制位标志位：有且仅有一个位为1
     *
     * @param flagBit flag bit;
     * @return true/false
     */
    private boolean invalidFlagBit(long flagBit) {
        return flagBit == 0 || (flagBit & (flagBit - 1L)) != 0;
    }

    public LogicBit init(long flagBit) {
        if (invalidFlagBit(flagBit)) {
            throw new RuntimeException("The number must be a power of 2.");
        }

        isInit = true;

        if (isLogicAnd) {
            flagValue |= flagBit;
        }

        return this;
    }

    /**
     * 对指定的当前位的值做逻辑处理
     *
     * @param flagBit   标志位
     * @param logicTrue 当前位的逻辑结果
     */
    public void flagBit(long flagBit, boolean logicTrue) {
        if (invalidFlagBit(flagBit)) {
            throw new RuntimeException("The number must be a power of 2.");
        }

        if (isLogicAnd) {
            flagValue = logicTrue ? flagValue & ~flagBit : (flagValue | flagBit);
        } else {
            flagValue = logicTrue ? (flagValue | flagBit) : flagValue & ~flagBit;
        }
    }

    public void logicTrue(long flagBit) {
        if (invalidFlagBit(flagBit)) {
            throw new RuntimeException("The number must be a power of 2.");
        }

        if (isLogicAnd) {
            // 当前位置0
            flagValue = flagValue & ~flagBit;
        } else {
            // 当前位置1
            flagValue = flagValue | flagBit;
        }
    }

    /**
     * 逻辑与全部条件满足，逻辑或某个条件满足
     *
     * @return 逻辑结果：true/false
     */
    public boolean logicResult() {
        if (!isInit) {
            return false;
        }

        if (isLogicAnd) {
            return flagValue == 0;
        } else {
            return flagValue != 0;
        }
    }
}
