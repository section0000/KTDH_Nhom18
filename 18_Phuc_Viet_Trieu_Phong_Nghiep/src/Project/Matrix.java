/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

/**
 *
 * @author admin
 */
public class Matrix {
   
    // Chuyen goc quay tu do sang radian
    public static double convertToRadian(double alpha)
    {
        return Math.PI*alpha/180;
    }    
    
    // Chuyen 1 diem sang ma tran [1,3]. VD: P(x1,y1) -> P(x1,y1,1)
    public static double[] convertPointToMatrix(Point pt)
    {
        double[] result = new double[3];
        result[0] = pt.getX();
        result[1]= pt.getY();
        result[2] = 1;
        return result;
    }

    // Chuyen tu ma tran ve lai diem (nguoc lai voi o tren)
    public static Point converMatrixToPoint(double[] matrix)
    {
        Point pt = new Point();
        if (matrix.length == 0 || matrix.length > 3)
        {
            System.out.println("Matrix is not valid");
        }
        pt.setX(matrix[0]);
        pt.setY(matrix[1]);
        return pt;
    }
    
    // Nhan 2 ma tran: [1,3]x[3,3]
    public static Point multiplyMatrix(double[] array, double[][] matrix2D)
    {
        double[] tempArray = new double[3];

        int count = 0;
        for (int i = 0; i < 3; i++)
        {
            tempArray[i] = array[0]*matrix2D[0][count] + array[1]*matrix2D[1][count] + array[2]*matrix2D[2][count];
            count++;
        }

        Point pt = new Point(tempArray[0], tempArray[1]);
        return pt;
    }    

    // Ma tran tinh tien ve goc toa do
    //   ( 1, 0,0)
    //   ( 0, 1,0)
    //   (-x,-y,1)
    public static double[][] initializeTranslateToOMatrix(double[][] matrix2D, Point displacement)
    {        
        if (matrix2D.length == 0 || matrix2D == null)
        {
            matrix2D = new double[3][3];
            matrix2D[0][0] = 1;                    matrix2D[0][1] = 0;                    matrix2D[0][2] = 0;
            matrix2D[1][0] = 0;                    matrix2D[1][1] = 1;                    matrix2D[1][2] = 0;
            matrix2D[2][0] = -displacement.getX(); matrix2D[2][1] = -displacement.getY(); matrix2D[2][2] = 1;  
            return matrix2D;
        }
        matrix2D[0][0] = 1;                     matrix2D[0][1] = 0;                    matrix2D[0][2] = 0;
        matrix2D[1][0] = 0;                     matrix2D[1][1] = 1;                    matrix2D[1][2] = 0;
        matrix2D[2][0] = -displacement.getX();  matrix2D[2][1] = -displacement.getY(); matrix2D[2][2] = 1;  
        return matrix2D;        
    }

    // Ma tran tinh tien
    //   (1,0,0)
    //   (0,1,0)
    //   (x,y,1)
    public static double[][] initializeTranslationMatrix(double[][] matrix2D, Point displacement)
    {        
        if (matrix2D.length == 0 || matrix2D == null)
        {
//            matrix2D = new double[3][3];
//            matrix2D[0][0] = 1; matrix2D[0][1] = 0; matrix2D[0][2] = pt.getX();
//            matrix2D[1][0] = 0; matrix2D[1][1] = 1; matrix2D[1][2] = pt.getY();
//            matrix2D[2][0] = 0; matrix2D[2][1] = 0; matrix2D[2][2] = 1;  
//            return matrix2D;
            matrix2D = new double[3][3];
            matrix2D[0][0] = 1;                   matrix2D[0][1] = 0;                   matrix2D[0][2] = 0;
            matrix2D[1][0] = 0;                   matrix2D[1][1] = 1;                   matrix2D[1][2] = 0;
            matrix2D[2][0] = displacement.getX(); matrix2D[2][1] = displacement.getY(); matrix2D[2][2] = 1;  
            return matrix2D;
        }
        matrix2D[0][0] = 1;                   matrix2D[0][1] = 0;                   matrix2D[0][2] = 0;
        matrix2D[1][0] = 0;                   matrix2D[1][1] = 1;                   matrix2D[1][2] = 0;
        matrix2D[2][0] = displacement.getX(); matrix2D[2][1] = displacement.getY(); matrix2D[2][2] = 1;  
        return matrix2D;        
    }
    
    // Ma tran quay quanh goc toa do 1 goc a
    //   Nguoc chieu kim dong ho:
    //    (cosa, -sina, 0)
    //    (sina,  cosa, 0)
    //    (0,      0,   1)
    //   Cung chieu kim dong ho: dao dau cua 2 cai sina lai
    public static double[][] initializeRotationMatrix(double[][] matrix2D, double alpha)
    {
        double sin = Math.sin(convertToRadian(alpha));
        double cos = Math.cos(convertToRadian(alpha));
        
        if (matrix2D.length == 0 || matrix2D == null)
        {
            matrix2D = new double[3][3];
            matrix2D[0][0] = cos; matrix2D[0][1] = -sin; matrix2D[0][2] = 0;
            matrix2D[1][0] = sin; matrix2D[1][1] = cos;  matrix2D[1][2] = 0;
            matrix2D[2][0] = 0;   matrix2D[2][1] = 0;    matrix2D[2][2] = 1;  
            return matrix2D;
        }
        matrix2D[0][0] = cos; matrix2D[0][1] = -sin; matrix2D[0][2] = 0;
        matrix2D[1][0] = sin; matrix2D[1][1] = cos;  matrix2D[1][2] = 0;
        matrix2D[2][0] = 0;   matrix2D[2][1] = 0;    matrix2D[2][2] = 1;  
        return matrix2D;        
    }    

    // Ma tran bien doi ti le
    public static double[][] initializeScalingMatrix(double[][] matrix2D, Point ratio)
    {        
        if (matrix2D.length == 0 || matrix2D == null)
        {
            matrix2D = new double[3][3];
//            matrix2D[0][0] = 1; matrix2D[0][1] = 0; matrix2D[0][2] = 0;
//            matrix2D[1][0] = 0; matrix2D[1][1] = 1; matrix2D[1][2] = 0;
//            matrix2D[2][0] = 0; matrix2D[2][1] = 0; matrix2D[2][2] = 1; 
            matrix2D[0][0] = ratio.getX(); matrix2D[0][1] = 0;            matrix2D[0][2] = 0;
            matrix2D[1][0] = 0;            matrix2D[1][1] = ratio.getY(); matrix2D[1][2] = 0;
            matrix2D[2][0] = 0;            matrix2D[2][1] = 0;            matrix2D[2][2] = 1;
            return matrix2D;
        }
        matrix2D[0][0] = ratio.getX(); matrix2D[0][1] = 0;            matrix2D[0][2] = 0;
        matrix2D[1][0] = 0;            matrix2D[1][1] = ratio.getY(); matrix2D[1][2] = 0;
        matrix2D[2][0] = 0;            matrix2D[2][1] = 0;            matrix2D[2][2] = 1;  
        return matrix2D;        
    }        
  
    // Cac ma tran cua phep doi xung qua Ox, Oy, O
    public static double[][] initializeSymmetricWithRespectToOxMatrix(double[][] matrix2D)
    {        
        if (matrix2D.length == 0 || matrix2D == null)
        {
            matrix2D = new double[3][3];
            matrix2D[0][0] = 1; matrix2D[0][1] = 0;  matrix2D[0][2] = 0;
            matrix2D[1][0] = 0; matrix2D[1][1] = -1; matrix2D[1][2] = 0;
            matrix2D[2][0] = 0; matrix2D[2][1] = 0;  matrix2D[2][2] = 1;
            return matrix2D;
        }
        matrix2D[0][0] = 1; matrix2D[0][1] = 0;  matrix2D[0][2] = 0;
        matrix2D[1][0] = 0; matrix2D[1][1] = -1; matrix2D[1][2] = 0;
        matrix2D[2][0] = 0; matrix2D[2][1] = 0;  matrix2D[2][2] = 1;
        return matrix2D;        
    }       

    public static double[][] initializeSymmetricWithRespectToOyMatrix(double[][] matrix2D)
    {        
        if (matrix2D.length == 0 || matrix2D == null)
        {
            matrix2D = new double[3][3];
            matrix2D[0][0] = -1; matrix2D[0][1] = 0;  matrix2D[0][2] = 0;
            matrix2D[1][0] = 0; matrix2D[1][1] = 1; matrix2D[1][2] = 0;
            matrix2D[2][0] = 0; matrix2D[2][1] = 0;  matrix2D[2][2] = 1;
            return matrix2D;
        }
        matrix2D[0][0] = -1; matrix2D[0][1] = 0;  matrix2D[0][2] = 0;
        matrix2D[1][0] = 0; matrix2D[1][1] = 1; matrix2D[1][2] = 0;
        matrix2D[2][0] = 0; matrix2D[2][1] = 0;  matrix2D[2][2] = 1;
        return matrix2D;        
    }       

    public static double[][] initializeSymmetricWithRespectToOMatrix(double[][] matrix2D)
    {        
        if (matrix2D.length == 0 || matrix2D == null)
        {
            matrix2D = new double[3][3];
            matrix2D[0][0] = -1; matrix2D[0][1] = 0;  matrix2D[0][2] = 0;
            matrix2D[1][0] = 0; matrix2D[1][1] = -1; matrix2D[1][2] = 0;
            matrix2D[2][0] = 0; matrix2D[2][1] = 0;  matrix2D[2][2] = 1;
            return matrix2D;
        }
        matrix2D[0][0] = -1; matrix2D[0][1] = 0;  matrix2D[0][2] = 0;
        matrix2D[1][0] = 0; matrix2D[1][1] = -1; matrix2D[1][2] = 0;
        matrix2D[2][0] = 0; matrix2D[2][1] = 0;  matrix2D[2][2] = 1;
        return matrix2D;        
    }         
}
