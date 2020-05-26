/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        // write your code here
        int num = 0;
        switch (operator) {
            case "MAX":

                for (int j = 1; j < args.length; j++) {
                  if (Integer.parseInt(args[j] ) > num) {
                      num = Integer.parseInt(args[j]);
                  }
                }
                break;

            case "MIN":
                num = Integer.MAX_VALUE;
                int min;
                for (int i = 1; i < args.length; i++) {
                     min = Integer.parseInt(args[i]);
                    if (min < num) {
                        num = min;
                    }
                }
                break;

            case "SUM":
                for (int i = 1; i < args.length; i++) {
                    num += Integer.parseInt(args[i]);
                }
                break;
        }
        System.out.println(num);
    }
}