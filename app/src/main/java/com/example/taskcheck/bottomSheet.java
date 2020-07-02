package com.example.taskcheck;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class bottomSheet extends BottomSheetDialogFragment
{
   BottomSheetListener bottomSheetListener;
   SeekBar seekBar;
   TextView txtProgress;
  int size;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.bottomsheet,container,false);

         seekBar=(SeekBar)view.findViewById(R.id.setTextSize);
         txtProgress=(TextView)view.findViewById(R.id.txtSizeprogress);

         seekBar.setMax(20);
         seekBar.setProgress(18);
         txtProgress.setText(String.valueOf(seekBar.getProgress())+"/"+20);
          size=seekBar.getProgress();

          seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                   if(b) {
                        seekBar.setProgress(i);
                       size=seekBar.getProgress();
                       bottomSheetListener.onButtonClicked(size);
                       txtProgress.setText(String.valueOf(seekBar.getProgress())+"/"+20);
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


    public  interface BottomSheetListener
   {
     void onButtonClicked(int size);
   }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        try {
            bottomSheetListener = (BottomSheetListener) context;
        }

       catch (ClassCastException e)
       {
           throw new ClassCastException(context.toString()+" must implement bottom sheet");
       }
    }

}
