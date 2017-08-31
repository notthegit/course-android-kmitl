package kmitl.lab03.notthegit.SimpleMyDot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.Random;
import kmitl.lab03.notthegit.SimpleMyDot.model.Dot;
import kmitl.lab03.notthegit.SimpleMyDot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener{

    private DotView dotView;
    private Dot dot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView)findViewById(R.id.dotView);
        dot = new Dot(this, 0, 0, 30);
    }

    public void onRandomDot(View view){
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
    }
    public void onClean(View view){
        this.dot.setCenterX(-300);
        this.dot.setCenterY(-300);
    }

    public boolean onTouchEvent(MotionEvent e) {
        //if(view.getId()==R.id.dotView){
            float x = e.getX();
            float y = e.getY();
            this.dot.setCenterX(Math.round(x));
            this.dot.setCenterY(Math.round(y));
            return true;
        //}
        //else
            //return false;
    }


        @Override
    public void onDotChanged(Dot dot){
        dotView.setDot(dot);
        dotView.invalidate();
    }

}
