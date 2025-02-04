package lab2;

public class App {

    //task_1
    private static String MaxUniqueSubstring(String inputStr) {
        String out = "";

        for(int i = 0; i < inputStr.length(); i ++){
            if(out.indexOf(inputStr.charAt(i)) == -1){
                out += inputStr.charAt(i);
            }
        }

        return out;
    }

    //task_3
    public static int FindMaxSubArraySum(int[] array) {
        int maxSum = array[0];
        int currentSum = 0;

        for (int num : array) {
            if (currentSum < 0) {
                currentSum = 0;
            }
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    //task_2
    public static int[] MergeAndSortArrays(int[] array1, int[] array2) {
        int[] mergedArray = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, mergedArray, 0, array1.length);
        System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);

        for (int i = 0; i < mergedArray.length; i++) {
            for (int j = i + 1; j < mergedArray.length; j++) {
                if (mergedArray[j] < mergedArray[i]) {
                    int temp = mergedArray[j];
                    mergedArray[j] = mergedArray[i];
                    mergedArray[i] = temp;
                }
            }
        }

        return mergedArray;
    }

    //task_6
    public static int SumOf2DArray(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sum += num;
            }
        }
        return sum;
    }

    public static void Print2DArray(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    //task_7
    public static int[] FindMaxInEachRow(int[][] matrix) {
        int[] maxValues = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int num : matrix[i]) {
                if (num > max) {
                    max = num;
                }
            }
            maxValues[i] = max;
        }
        return maxValues;
    }

    //task_4
    public static int[][] RotateMatrixClockwise(int[][] matrix) {
        int[][] rotated = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rotated[j][matrix.length - i - 1] = matrix[i][j];
            }
        }
        return rotated;
    }

    //task_8
    public static int[][] RotateMatrixCounterClockwise(int[][] matrix) {
        int[][] rotated = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rotated[matrix.length - j - 1][i] = matrix[i][j];
            }
        }
        return rotated;
    }

    //task_5
    public static int[] FindTwoNumbersWithSum(int[] array, int target) {
        int[] result = new int[2];
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == target) {
                    result[0] = array[i];
                    result[1] = array[j];
                    return result;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //task_1
        String example = "abujejkfojiabb";
        System.out.println(MaxUniqueSubstring(example));
        System.out.println();

        //task_2
        int[] array1 = {1,2,3,6,8};
        int[] array2 = {-5,-1,4,7,9};
        int[] answer = MergeAndSortArrays(array1, array2);
        for(int i = 0; i < answer.length; i ++){
            System.out.print(answer[i] + " ");
        }
        System.out.println();

        //task_3
        int[] array = {-3,11,2,-8,9,15,-1};
        System.out.println(FindMaxSubArraySum(array));
        System.out.println();

        //task_4
        int[][] mat = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        Print2DArray(RotateMatrixClockwise(mat));

        //task_5
        int[] arr = {1,2,3,9,0,0,0,1,2,3};
        int[] ans = FindTwoNumbersWithSum(arr, 12);
        System.out.println(ans[0] + " " + ans[1]);
        System.out.println();

        //task_6
        System.out.println(SumOf2DArray(mat));
        System.out.println();

        //task_7
        int[] array7 = FindMaxInEachRow(mat);
        for(int i = 0; i < array7.length; i ++){
            System.out.print(array7[i] + " ");
        }
        System.out.println();

        //task_8
        Print2DArray(RotateMatrixCounterClockwise(mat));
    }
}
