class DAG_Distance_main {
    public static void main(String[] args) {
        try {
            RunTests tests = new RunTests();
            tests.runTests();
            System.out.println("All test cases passed!");
      } catch (RuntimeException e) {
            System.err.println("Test failed:");
            System.err.println(e.getMessage());
      }
    }
}

