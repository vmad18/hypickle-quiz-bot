package mod.quizbot.me.utils.mathstuff;


import java.util.Random;

public class Matrices {

    public int[][] mat1;

    public int[][] mat2;

    public Matrices(int[][] m, int[][] m1){
        mat1 = m;
        mat2 = m1;
    }

    public Matrices(){
        mat1 = generateRandomMatrix();
        mat2 = generateRandomMatrix();
    }

    public int[][] getMat1() {
        return mat1;
    }

    public int[][] getMat2() {
        return mat2;
    }

    public int[][] generateRandomMatrix(){
        Random r = new Random();
        int[][] mat = new int[3][3];
        int[] hold = new int[3];
        for(int i=0;i<9;i++){
            hold[(i)%3] = r.nextInt(9)+1;
            if((i+1)%3==0){
                mat[(i+1)%3] = hold;
            }
        }
        return mat;
    }

    public double matrixMultiplication(){
        int sum=0;
        int size = mat1.length;
        int[][] new_mat = new int[size][size];
        for(int mrow=0;mrow<size;mrow++) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < mat1.length; col++) {
                    sum += mat2[col][mrow] * mat1[row][col];
                }
                new_mat[row][mrow] = sum;
                sum = 0;
            }
        }
        return Determinate(new_mat);
    }

    public double Determinate(int[][] mat){
        int[] m = mat[0];
        int[][] hold = new int[mat.length-1][mat[1].length];
        int d=0;
        int a=1;
        for(int i=1;i<mat.length;i++){
            hold[i-1] = mat[i].clone();
        }
        for(int i=1;i<hold[0].length+1;i++){
            if(i%hold[0].length>(i+1)%hold[0].length){
                d+=a*m[i-1]*(hold[0][(i+1)%hold[0].length]*hold[1][i%hold[0].length]-hold[0][i%hold[0].length]*hold[1][(i+1)%hold[0].length]);
            }else{
                d+=a*m[i-1]*(hold[0][i%hold[0].length]*hold[1][(i+1)%hold[0].length]-hold[0][(i+1)%hold[0].length]*hold[1][i%hold[0].length]);
            }
            a*=-1;
        }
        return d;
    }
}
