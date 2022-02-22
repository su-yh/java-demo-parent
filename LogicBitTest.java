class LogicBitTest {
    private static final SecureRandom secureRandom = new SecureRandom();

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("randProvider")
    public void testLogicAndSingle(int flagBitCount) {
        LogicBit logicBit = new LogicBit(true);

        logicBit.init(1L << flagBitCount);
        Assertions.assertFalse(logicBit.logicResult());

        logicBit.logicTrue(1L << flagBitCount);
        Assertions.assertTrue(logicBit.logicResult());
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("rangeProvider")
    public void testLogicAndMulti(int flagBitCount) {
        LogicBit logicBit = new LogicBit(true);

        for (int flagIndex = 0; flagIndex < flagBitCount; flagIndex++) {
            long flagBit = 1L << flagIndex;
            logicBit.init(flagBit);
            Assertions.assertFalse(logicBit.logicResult());
        }

        for (int flagIndex = 0; flagIndex < flagBitCount; flagIndex++) {
            long flagBit = 1L << flagIndex;
            logicBit.logicTrue(flagBit);
            if (flagIndex == flagBitCount - 1) {
                Assertions.assertTrue(logicBit.logicResult());
            } else {
                Assertions.assertFalse(logicBit.logicResult());
            }
        }
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("randProvider")
    public void testLogicOrSingle(int flagBitCount) {
        LogicBit logicBit = new LogicBit(false);

        logicBit.init(1L << flagBitCount);
        Assertions.assertFalse(logicBit.logicResult());

        logicBit.logicTrue(1L << flagBitCount);
        Assertions.assertTrue(logicBit.logicResult());
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("rangeProvider")
    public void testLogicOrMulti(int flagBitCount) {
        LogicBit logicBit = new LogicBit(false);

        for (int flagIndex = 0; flagIndex < flagBitCount; flagIndex++) {
            long flagBit = 1L << flagIndex;
            logicBit.init(flagBit);
            Assertions.assertFalse(logicBit.logicResult());
        }

        for (int flagIndex = 0; flagIndex < flagBitCount; flagIndex++) {
            long flagBit = 1L << flagIndex;
            logicBit.logicTrue(flagBit);
            Assertions.assertTrue(logicBit.logicResult());
        }
    }

    // this need static
    static IntStream randProvider() {
        return secureRandom.ints(1000, 1, 65);
    }

    // this need static
    static IntStream rangeProvider() {
        return IntStream.range(1, 65);
    }
