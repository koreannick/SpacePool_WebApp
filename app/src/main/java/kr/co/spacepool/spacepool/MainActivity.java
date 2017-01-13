package kr.co.spacepool.spacepool;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends FragmentActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /*
	mWebView관련 아래 코드들은 WebView 설정과 관련된 부분들이다.
	필요한 부분만 사용하시면 됩니다.
	 */
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setBackgroundColor(255); //배경색
        mWebView.setHorizontalScrollBarEnabled(false); //가로 스크롤
        mWebView.setVerticalScrollBarEnabled(false); //세로 스크롤
        //mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY); //스크롤 노출타입

        //HTML을 파싱하여 웹뷰에서 보여주거나 하는 작업에서
        //width , height 가 화면 크기와 맞지 않는 현상이 발생한다
        //이를 잡아주기 위한 코드
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //캐시파일 사용 금지(운영중엔 주석처리 할 것)
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        //zoom 허용
        //mWebView.getSettings().setBuiltInZoomControls(true);
        //mWebView.getSettings().setSupportZoom(true);

        //javascript의 window.open 허용
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //javascript 허용
        mWebView.getSettings().setJavaScriptEnabled(true);

        //meta태그의 viewport사용 가능
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.loadUrl("http://www.spacepool.co.kr//");
        mWebView.setWebViewClient(new WishWebViewClient());
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WishWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}