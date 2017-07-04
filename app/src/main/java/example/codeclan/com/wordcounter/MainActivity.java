package example.codeclan.com.wordcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity
{
    EditText edit_input;
    Button btn_count;
    TextView txt_result;
    TextView txt_counts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Must come after setContentView
        edit_input = (EditText)findViewById(R.id.edit_input);
        txt_result = (TextView)findViewById(R.id.txt_result);
        btn_count = (Button)findViewById(R.id.btn_count);
        txt_counts = (TextView)findViewById(R.id.txt_counts);
    }

    public void onCountButtonClicked(View button)
    {
        //Get string, replace punctuation with empty strings
        String countText = edit_input.getText().toString().replaceAll("[\\p{Punct}]", "");

        ArrayList<String> splitStrings = new ArrayList<String>();
        //Snazzy regex for multiple spaces
        for(String string : countText.split(" +"))
        {
            splitStrings.add(string);
        }

        txt_result.setText(Integer.toString(splitStrings.size()));

        HashSet<String> tokens = new HashSet<String>();
        for(String splitString : splitStrings)
        {
            tokens.add(splitString);
        }

        class StringCount
        {
            private String word;
            private int count;

            public StringCount(String word, int count)
            {
                this.word = word;
                this.count = count;
            }

            public String getWord()
            {
                return word;
            }

            public int getCount()
            {
                return count;
            }
        }

        ArrayList<StringCount> counts = new ArrayList<StringCount>();
        for(String token : tokens)
        {
            int wordCount = 0;

            for(String splitString : splitStrings)
            {
                if(splitString.equals(token))
                {
                    wordCount++;
                }
            }

            counts.add(new StringCount(token, wordCount));
        }

        Collections.sort(counts, new Comparator<StringCount>() {
            @Override
            public int compare(StringCount z1, StringCount z2) {
                if (z1.getCount() > z2.getCount())
                    return 1;
                if (z1.getCount() < z2.getCount())
                    return -1;
                return 0;
            }
        });

        StringBuilder sb = new StringBuilder();
        for(StringCount count : counts)
        {
            sb.append(String.format("\"%s\": %d, ", count.getWord(), count.getCount()));
        }

        txt_counts.setText(sb.toString());
    }
}
