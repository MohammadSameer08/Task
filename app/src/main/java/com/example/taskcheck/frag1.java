package com.example.taskcheck;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class frag1 extends Fragment
{

     SeekBar seekBar;
     TextView textView,txtProgress,txtPageNo;
      ImageButton btnNext,btnPrev;
     int height;
     int pageNo=0,pNo=1;

     String str[]={"Even though Israel is a small country it is always in the news.  The news continues to report on Jews moving to Israel, on the technology invented there, but also on conflict, wars and tensions with surrounding people.  Why? A look at Israel’s history in the book of Genesis in the Bible reveals that 4000 years ago a man, who is now very well known, went on a camping trip in that part of the world.  The Bible says that his story affects our future.This ancient man is Abraham (also known as Abram).  We can take his story seriously because the places and cities he visited are mentioned in other old writings.“I will make you into a great nation, and I will bless you",

                  "I will make your name great, and you will be a blessing.I will bless those who bless you, and whoever curses you I will curse",

                  "and all peoples on earth will be blessed through you.” (Genesis 12:2-3)Most of us wonder if there is a God and if He really is the God of the Bible. In the Bible God says ‘I will make your name great’ and today the name of Abraham/Abram is known worldwide. This promise has come true. The earliest copy of Genesis found in the Dead Sea Scrolls is dated 200-100 B.C. which means the promise has been in writing since at least that time. At that time the name of Abraham was not well-known so the promise came true only after it was written down, not before.Surprisingly Abraham really did nothing important in his life.  He was not a great writer, king, inventor or military leader.  He did nothing except camp out where he was told to go and father a few children.  His name is great only because the children became nation(s) that kept the record of his life – and then individuals and nations that came from him became great.  This is exactly how it was promised in Genesis 12 (“I will make you into a great nation … I will make your name great”).  No one else in all history is so well-known only because of descendants rather than from great achievements in his own lifeThe Jews who descended from Abraham were never really the nation we associate with greatness.  They did not conquer and build a great empire like the Romans did or build large monuments like the Egyptians did with the pyramids. Their fame comes from the Law and Book which they wrote; from some remarkable individuals that were Jewish; and that they have survived as a somewhat different people group for thousands of years.  Their greatness is not because of anything they did, but rather what was done to and through them.  The promise says repeatedly “I will …” – that would be the power behind the promise.  Their unique greatness happened because God made it happen rather than some ability, conquest or power of their own.",

                    "The promise to Abraham came true because he trusted a promise and chose to live differently than others. Think how likely it was for this promise to have failed, but instead it has happened, and is continuing to unfold, as it was stated  thousands of years ago.  The case is strong that the promise came true only because of the power and authority of the Promise-Maker."};



     @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view=inflater.inflate(R.layout.frag1,container,false);
           textView=(TextView)view.findViewById(R.id.textView);
           txtProgress=(TextView)view.findViewById(R.id.progress);
           txtPageNo=(TextView) view.findViewById(R.id.txtpageNo);
           seekBar=(SeekBar)view.findViewById(R.id.seekBar);
           btnNext=(ImageButton)view.findViewById(R.id.btnNext);
           btnPrev=(ImageButton)view.findViewById(R.id.btnprev);

         SharedPreferences sharedPreferences=getActivity().getSharedPreferences("com.example.taskcheck",Context.MODE_PRIVATE);
         int size=sharedPreferences.getInt("size",18);


        textView.setText(str[0]);
        textView.setTextSize(size);
        txtPageNo.setText(pNo+"/"+str.length);
        dimension();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(pageNo < str.length) {
                    txtPageNo.setText(pNo++ + "/" + str.length);
                    seekBar.setProgress(0);
                    textView.setText(str[pageNo++]);
                    dimension();
                }
              else
                    Toast.makeText(getActivity(),"You are in Last Page!" ,Toast.LENGTH_SHORT).show();

            }
        });

         btnPrev.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             {
                 if(pageNo !=0) {
                     pageNo--;
                     pNo--;
                     txtPageNo.setText(pNo + "/" + str.length);
                     seekBar.setProgress(0);
                     textView.setText(str[pageNo]);
                     dimension();
                 }
                 else
                     Toast.makeText(getActivity(),"You are in First Page!" ,Toast.LENGTH_SHORT).show();

             }
         });

         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(final SeekBar seekBar, int i, boolean b)
           {
               if(b)
              {
                      textView.scrollTo(0, i);
                      txtProgress.setText(String.valueOf(seekBar.getProgress()) + "/" + String.valueOf(height));

              }


           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });

        return view;
    }

    public  void dimension()
    {
        ViewTreeObserver vto = textView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                    textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                else
                    textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                View myView = textView;
                // here height is not 0
                Layout layout=textView.getLayout();
                height =layout.getHeight()-textView.getHeight();
                 if(height<0)
                 {
                     height =layout.getHeight();

                 }
                seekBar.setMax(height);
                txtProgress.setText(String.valueOf(seekBar.getProgress())+"/"+String.valueOf(height));
            }
        });



    }


   }
