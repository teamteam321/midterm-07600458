package com.akzaki.speedcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button clear = findViewById(R.id.clear_button);
        final Button calculate = findViewById(R.id.calculate_button);
        final TextView distance = findViewById(R.id.input_distance);
        final TextView second = findViewById(R.id.input_second);
        final TextView out = findViewById(R.id.output_speed);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                distance.setText("");
                second.setText("");
                out.setText("");
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out.setText("");
                try{
                    double dis = Double.parseDouble(distance.getText().toString());
                    double tm = Double.parseDouble(second.getText().toString());
                    if(tm == 0.0){
                        Toast msg = Toast.makeText(MainActivity.this, R.string.time_greater_zero,Toast.LENGTH_LONG);
                        msg.show();
                        return;
                    }
                    dis /= 1000;
                    tm /= 3600;
                    double out_spd = dis/tm;
                    //out.setText(String.format("%.2f",out_spd));
                    out.setText(String.format(Locale.getDefault(), "%.2f", out_spd));
                    if(out_spd > 80.0){
                        AlertDialog.Builder result_a = new AlertDialog.Builder(MainActivity.this);
                        result_a.setTitle("SPEED CALCULATOR");
                        result_a.setMessage(R.string.over_limit);
                        result_a.setPositiveButton("OK",null);
                        result_a.show();
                    }
                }catch(Exception e){
                    Toast msg = Toast.makeText(MainActivity.this, R.string.distance_time_required,Toast.LENGTH_LONG);
                    msg.show();
                }

            }
        });

    }
}