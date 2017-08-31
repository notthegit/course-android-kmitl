package kmitl.lab03.notthegit.SimpleMyDot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import kmitl.lab03.notthegit.SimpleMyDot.model.Dot;
import java.util.Random;

public class DotView extends View {
    private Dot dot;

    private Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Random rand = new Random();
        int color = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        paint.setColor(color);
        if(this.dot != null) {
            canvas.drawCircle(this.dot.getCenterX(), this.dot.getCenterY(), 30, paint);
        }
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDot(Dot dot){
        this.dot = dot;
    }
}
