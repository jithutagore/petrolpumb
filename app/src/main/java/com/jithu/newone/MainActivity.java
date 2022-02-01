package com.jithu.newone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.jithu.newone.adapter.ParseItemAdapter;
import com.jithu.newone.model.ParseItemModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Tagggg";
    private RecyclerView recyclerView;
    private ParseItemAdapter parseItemAdapter;
    private final List<ParseItemModel> parseItemModelList = new ArrayList<>();
    Elements data;
    Document document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView_id);
        parseItemAdapter = new ParseItemAdapter((ArrayList<ParseItemModel>) parseItemModelList, this);
        recyclerView.setAdapter(parseItemAdapter);

        Content content= new Content();
        content.execute();
    }
    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPostExecute(Void unused) {
            super.onPreExecute();

            parseItemAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url="https://www.google.com/search?q=petrol%20pump&biw=982&bih=754&tbm=lcl&sxsrf=APq-WBurzW9atv_DwnEr_oB4fHcNRDkDaA%3A1643697433252&ei=GdX4YcTxDvKC4-EPxIu06AI&oq=petrol+p&gs_l=psy-ab.1.0.0i433i67k1j0i512i433i457k1j0i512i433k1j0i512k1j0i512i433k1j0i67k1j0i512k1j0i512i433k1l2j0i512k1.363302.363834.0.365510.3.3.0.0.0.0.209.333.0j1j1.2.0....0...1c.1.64.psy-ab..1.2.332....0.ri2VCNUYTkg&tbs=lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2&rlst=f#rlfi=hd:;si:;mv:[[8.904745700000001,76.6090775],[8.8782559,76.5604994]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
                document= Jsoup.connect(url).get();
                data=document.select("div.rllt__details");
//                Log.e(TAG, "Get Data: "+data.toString() );
                Log.i(TAG, "doInBackground: Hellooo");

                int size=data.size();
                for (int i =0; i <size; i++){
                    Element s = data.get(i);
//                    hii
                    String name = s.select("div.dbg0pd > span").text();
                    String rating = s.select("span.MvDXgc").text();
                    String status = s.select("div").last().text();

                    parseItemModelList.add(new ParseItemModel(name,rating,status));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
