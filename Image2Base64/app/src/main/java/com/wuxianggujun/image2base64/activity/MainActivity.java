package com.wuxianggujun.image2base64.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.wuxianggujun.image2base64.R;
import java.io.ByteArrayOutputStream;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import com.wuxianggujun.image2base64.utils.StringZip;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tv;
    private ImageView image;
    private boolean isText = true;
    String imagePath = "/storage/emulated/0/AppProjects/Image2Base64/image.jpg";
    Bitmap bitmap;
    
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    if(!isText){
                        btn.setText("图片转字符串");
                        isText = true;
                    }else{
                    btn.setText("字符串转图片");
                        isText = false;
                    }
                    try {
                        String content = StringZip.zipBase64((String)msg.obj);
                        tv.setText(content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   
                    image.setImageBitmap(convertStringToIcon((String)msg.obj));
                    break;               
            }
       
        }     
    };
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);
        tv = findViewById(R.id.activitymainTextView1);
        image = findViewById(R.id.activitymainImageView1);    
		btn = findViewById(R.id.activitymainButton1);
        btn.setText("图片转字符串");
        //image.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                     bitmap= BitmapFactory.decodeFile(imagePath);
                     String content =  convertIconToString(bitmap);
                     if(content!= null){
                     Message msg = new Message();
                     msg.what = 1;
                     msg.obj = content;
                     mHandler.sendMessage(msg);  
                     }
                    Toast.makeText(getApplication(), "开始转换!", Toast.LENGTH_SHORT).show();
                }
                
                
            });
        
	}


    public String convertIconToString(Bitmap bitmap) { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream 
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组 return 
        return  Base64.encodeToString(appicon, Base64.DEFAULT);  
    }

    /**
     * string转成bitmap
     *  
     * @param st
     */
    public static Bitmap convertStringToIcon(String st) {
        // OutputStream out;
        Bitmap bitmap = null; try { 
            // out = new FileOutputStream("/sdcard/aa.jpg"); 
            byte[] bitmapArray; 
            bitmapArray = Base64.decode(st, Base64.DEFAULT); 
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); 
            return bitmap; 
        } catch (Exception e) { 
            return null; 
        } 
    }

}
