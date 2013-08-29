package mayi.slidingmenudemo;

import net.basilwang.niaopiao.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TicketDetails implements OnClickListener{

	private TextView txt;
	private Button bnt;
	boolean isOpen;
	public TicketDetails(TextView txtView,Button button){
		this.txt=txtView;
		this.bnt=button;
		isOpen=false;
		this.txt.setMaxLines(0);
		this.bnt.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(isOpen==false){
			this.txt.setMaxLines(200);
			this.bnt.setBackgroundResource(R.drawable.bg_layout_bnt2);
			isOpen=true;
		}else{
			this.txt.setMaxLines(0);
			this.bnt.setBackgroundResource(R.drawable.bg_layout_bnt);
			isOpen=false;
		}
	}
}
