package com.example.ccei.arrayadapterpj;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView taraList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taraList = (ListView)findViewById(R.id.tara_list);

         // = MainActivity.this , getApplication.... 도 this와 같은의미
        Resources res = getResources();

        ArrayList<TARAValueObject> items = new ArrayList<TARAValueObject>();
        items.add(new TARAValueObject("소연", res.getDrawable(R.drawable.t_ara_icon_soyeon)));
        items.add(new TARAValueObject("지연", res.getDrawable(R.drawable.t_ara_icon_jiyeon)));
        items.add(new TARAValueObject("규리", res.getDrawable(R.drawable.t_ara_icon_qri)));
        items.add(new TARAValueObject("보람", res.getDrawable(R.drawable.t_ara_icon_boram)));
        items.add(new TARAValueObject("효민", res.getDrawable(R.drawable.t_ara_icon_hyomin)));
        items.add(new TARAValueObject("은정", res.getDrawable(R.drawable.t_ara_icon_eunjung)));

        TARAArrayAdapter adapter = new TARAArrayAdapter(this, items);

        taraList.setAdapter(adapter);
        taraList.setOnItemClickListener(itemListener);
    }

    private AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//            LinearLayout itemRoot = (LinearLayout)view;     //우리 xml의 루트레이아웃
//            String memberNmae = ((TextView)((LinearLayout) view).getChildAt(1)).getText().toString();
            //를 통해서도 멤버이름을 가져올 수 있다.

            TARAValueObject valueObject = (TARAValueObject)parent.getItemAtPosition(position);
            Toast.makeText(MainActivity.this, valueObject.memberName +"을 좋아하는구낭", Toast.LENGTH_SHORT).show();
        }
    };

    //어댑터 생성
    // 가장 기본이 되는 어댑터. 개발자의 능력에 따라 자료구조를 다양하게 재정의하여 사용할 수 있다.
    // 유연성이 좋은 어댑터이다.
    private class TARAArrayAdapter extends ArrayAdapter<TARAValueObject> {
        private Context currentContext;

        public TARAArrayAdapter(Context context, List<TARAValueObject> objects) {
            super(context, 0, objects);
            this.currentContext = context;
        }


        /*
         이 메소드는 어댑터뷰에서 그려질 아이템의 개수만큼 호출된다.
         또한 화면에서 보여질 때마다 매번 호출된다!!!!!!!!!!!! 존나게 중요하다아아ㅏ!!!!
         */
        ViewHolder viewHolder;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TARAValueObject valueObject = (TARAValueObject) getItem(position);

            /* convertView : 그려질 아이템의 root(보통)값을 의미. 아이템에 그려질 위젯이 하나라면 그 위젯의 객체가 될 수 있음
            안드로이드 시스템에 의해 convertView 객체는 계속 재사용될 수 있음을 명심할 것*/
            // if문을 주지 않게되면 계속해서 list가 inflation을 하기때문에 속도가 느려지게된다. 그를 방지하기 위한 코드
            if (convertView == null) {
                convertView = LayoutInflater.from(currentContext).inflate(R.layout.tara_list_view_item, null);
                viewHolder = new ViewHolder();
                viewHolder = new ViewHolder();
                viewHolder.memberImageWD = (ImageView)convertView.findViewById(R.id.member_image);
                viewHolder.memberNameWD = (TextView)convertView.findViewById(R.id.member_name);
                convertView.setTag(viewHolder);

            }
            ViewHolder viewholder = (ViewHolder)convertView.getTag();
            viewholder.memberImageWD.setImageDrawable(valueObject.memeberImage);
            viewholder.memberNameWD.setText(valueObject.memberName);

            //한 행이 그려질 때 마다 리턴이 필요하다.
            return convertView;
        }

        private class ViewHolder{
            public ImageView memberImageWD;
            public TextView memberNameWD;

        }
    }
}
