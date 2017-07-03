package example.codeclan.com.wordcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    EditText edit_input;
    Button btn_count;
    TextView txt_result;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Must come after setContentView
        edit_input = (EditText)findViewById(R.id.edit_input);
        txt_result = (TextView)findViewById(R.id.txt_result);
        btn_count = (Button)findViewById(R.id.btn_count);
    }

    public void onCountButtonClicked(View button)
    {
        String countText = edit_input.getText().toString();
        ArrayList<String> splitStrings = new ArrayList<String>();
        for(String string : countText.split(" "))
        {
            splitStrings.add(string);
        }

        txt_result.setText(Integer.toString(splitStrings.size()));
    }
}
