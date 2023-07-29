package emart.pojo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class BarCode_IMG_Generator {
    public static void createImage(String image_name, String myString){
        try{
            Code128Bean code128=new Code128Bean();
            code128.setHeight(15f);//it set the  barcode's height
            ByteArrayOutputStream baos= new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos,"image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false);
            code128.generateBarcode(canvas,myString);// method to generate barcode code128 class ka instance methid hai
            // canvas vo parametr hai jiske pass barcode ki details hoti hai or myString vo parametr hai jo  barcode generate krne ka actual kaaam ye krta hai pojo samj sakte hai isko
            //jo id batata hai barcode ki
            canvas.finish();
            String userdir=System.getProperty("user.dir");
          //  System.out.println("user dir is : "+ userdir);
          //  System.out.println(userdir);
            FileOutputStream fos= new FileOutputStream(userdir+"\\Barcode\\"+ image_name);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            
        }
        catch(Exception e){
            System.out.println("Exception in Barcode gen: "+ e.getMessage());
            e.printStackTrace();
            
        }
    }
    public static void main(String[] args) {
     BarCode_IMG_Generator obj=   new BarCode_IMG_Generator();
    BarCode_IMG_Generator.createImage("Mine","Mine");
    }
}
