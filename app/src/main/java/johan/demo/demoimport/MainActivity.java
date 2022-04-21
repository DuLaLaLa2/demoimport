package johan.demo.demoimport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] texts = {"12","1","2","3","4","5","6","7","8","9","10","11"};
    private ConstraintLayout cl;
    private List<TextView> tvs = new ArrayList<>();
    private int angle;
    private ImageView iv1;
    private Chronometer cr1;

    ImageView miaozhen;
    ImageView fenzhen;
    ImageView shizhen;
    Calendar cal = null;
    int second;
    int minute;
    int hour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_layout);
        cl = findViewById(R.id.cl);
        for(int i = 0; i<=11; i++){
            TextView t1=new TextView(MainActivity.this);
            t1.setId(View.generateViewId());
            t1.setTextSize(24);
            t1.setText(texts[i]);
//            if(i==0){
//                t1.setText("12");
//            }else{
//                t1.setText(i+"");
//            }
            cl.addView(t1);
            tvs.add(t1);}
        ConstraintSet cst=new ConstraintSet();
        cst.clone(cl); //cl为窗体的布局

        iv1 = findViewById(R.id.imageView);
        shizhen = findViewById(R.id.imageView2);
        fenzhen = findViewById(R.id.imageView3);
        miaozhen = findViewById(R.id.imageView4);


//        miaozhen.setPivotX(0.5f);
//        miaozhen.setPivotY(0.5f);
//        fenzhen.setPivotX(0.5f);
//        fenzhen.setPivotY(0.5f);
//        shizhen.setPivotX(0.5f);
//        shizhen.setPivotY(0.5f);
        for(int i=0;i<=11;i++){
            int indx=i%12;
            TextView xx=tvs.get(indx);
            cst.connect(xx.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
            cst.connect(xx.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
            cst.connect(xx.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
            cst.connect(xx.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
            cst.constrainCircle(xx.getId(),iv1.getId(),480,angle); //这句是关键
            angle+=30;
            cst.applyTo(cl);
        }
        cr1 = findViewById(R.id.simpleChronometer);
        cr1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                setangles();  //设置时针、分针、秒针的角度变化。



            }
        });
        cal = Calendar.getInstance();

        cr1.start();
    }

    private void setangles() {
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
        float secondjd=second*6;
        float minutejd=minute*6+second*6/60;
        float hourjd=hour*30+minute*30/60+second*30/3600;
        miaozhen.setPivotX(miaozhen.getWidth()/2);
        miaozhen.setPivotY(0);
        fenzhen.setPivotX(fenzhen.getWidth()/2);
        fenzhen.setPivotY(0);
        shizhen.setPivotX(shizhen.getWidth()/2);
        shizhen.setPivotY(0);
        miaozhen.setRotation(secondjd+180);
        fenzhen.setRotation(minutejd+180);
        shizhen.setRotation(hourjd+180);
        cal.add(Calendar.SECOND,1);
    }

}