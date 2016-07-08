package com.example.ccei.arrayadapterpj;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView taraList;
    EditText count_e;
    ImageView likeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taraList = (ListView)findViewById(R.id.tara_list);
        likeImage = (ImageView)findViewById(R.id.like_img);



         // = MainActivity.this , getApplication.... 도 this와 같은의미
        Resources res = getResources();

        ArrayList<TARAValueObject> items = new ArrayList<TARAValueObject>();
        items.add(new TARAValueObject("소연", res.getDrawable(R.drawable.t_ara_icon_soyeon), 0));
        items.add(new TARAValueObject("지연", res.getDrawable(R.drawable.t_ara_icon_jiyeon), 0));
        items.add(new TARAValueObject("규리", res.getDrawable(R.drawable.t_ara_icon_qri), 0));
        items.add(new TARAValueObject("보람", res.getDrawable(R.drawable.t_ara_icon_boram), 0));
        items.add(new TARAValueObject("효민", res.getDrawable(R.drawable.t_ara_icon_hyomin),0));
        items.add(new TARAValueObject("은정", res.getDrawable(R.drawable.t_ara_icon_eunjung), 0));

        TARAArrayAdapter adapter = new TARAArrayAdapter(this, items);

        taraList.setAdapter(adapter);
        //taraList.setOnItemClickListener(itemListener);
    }


  /*
        아이템행을 터치하면 발생하는 이벤트이며 아이템안에 존재하는 각 위젯에 이벤트를 설정시
        itemClick 이벤트가 먼저 발생하게 되므로 이 이벤트는 설정하지 않는 것이 보편적이다.

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
*/
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


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final TARAValueObject valueObject = (TARAValueObject) getItem(position);
            final ViewHolder viewHolder;

            /* convertView : 그려질 아이템의 root(보통)값을 의미. 아이템에 그려질 위젯이 하나라면 그 위젯의 객체가 될 수 있음
            안드로이드 시스템에 의해 convertView 객체는 계속 재사용될 수 있음을 명심할 것*/
            // if문을 주지 않게되면 계속해서 list가 inflation을 하기때문에 속도가 느려지게된다. 그를 방지하기 위한 코드
            if (convertView == null) {
                convertView = LayoutInflater.from(currentContext).inflate(R.layout.tara_list_view_item, null);
                viewHolder = new ViewHolder();
                viewHolder.memberImageWD = (ImageView)convertView.findViewById(R.id.member_image);
                viewHolder.memberNameWD = (TextView)convertView.findViewById(R.id.member_name);
                viewHolder.LikeImageWD = (ImageView)convertView.findViewById(R.id.like_img);
                viewHolder.LikeCountWD = (TextView)convertView.findViewById(R.id.like_count);
                convertView.setTag(viewHolder);

            }else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.memberImageWD.setImageDrawable(valueObject.memeberImage);
            viewHolder.memberNameWD.setText(valueObject.memberName);

            viewHolder.memberImageWD.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Toast.makeText(currentContext, valueObject.memberName+"가 좋구나아?", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            viewHolder.LikeImageWD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    valueObject.likeCount++;
                    viewHolder.LikeCountWD.setText(String.valueOf(valueObject.likeCount));
                }
            });

            //한 행이 그려질 때 마다 리턴이 필요하다. root layout의
            return convertView;
        }

        private class ViewHolder{
            public ImageView memberImageWD;
            public TextView memberNameWD;
            public ImageView LikeImageWD;
            public TextView LikeCountWD;

        }
    }
}
