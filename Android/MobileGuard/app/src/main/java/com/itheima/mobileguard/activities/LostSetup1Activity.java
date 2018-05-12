package com.itheima.mobileguard.activities;

import com.itheima.mobileguard.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LostSetup1Activity extends BaseLostSetupActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_lostfindsetup1);
		}

		@Override
		public void showNext() {
			startActivityAndFinishSelf(LostSetup2Activity.class);
		}

		public void showPrevious() {
		}
}
