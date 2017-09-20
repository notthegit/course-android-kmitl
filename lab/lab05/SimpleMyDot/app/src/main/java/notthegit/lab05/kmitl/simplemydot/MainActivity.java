package notthegit.lab05.kmitl.simplemydot;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import notthegit.lab05.kmitl.simplemydot.model.Colors;
import notthegit.lab05.kmitl.simplemydot.model.Dot;
import notthegit.lab05.kmitl.simplemydot.model.Dots;
import notthegit.lab05.kmitl.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity
        implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener{

    private DotView dotView;
    private Dots dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);
        Button t = (Button) findViewById(R.id.t);
        t.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view){
                Fragment fragment;
                fragment = new EditDotFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerFragment, fragment);
                ft.commit();
                return true;
            }
        });
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.containerFragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, new Colors().getColor());
        dots.addDot(newDot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onRemoveAll(View view) {
        dots.clearAll();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if(dotPosition == -1) {
            Dot newDot = new Dot(x, y, 30, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.removeBy(dotPosition);
        }
    }
}