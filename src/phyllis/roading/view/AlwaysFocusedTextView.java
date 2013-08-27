package phyllis.roading.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class AlwaysFocusedTextView extends TextView
{
  public AlwaysFocusedTextView(Context paramContext)
  {
    super(paramContext);
  }

  public AlwaysFocusedTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public AlwaysFocusedTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean isFocused()
  {
    return true;
  }
}