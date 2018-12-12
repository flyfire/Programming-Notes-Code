package net.println.kotlin.chapter3;

public class Player {
    enum State {
        IDLE, BUFFERING, PLAYING, PAUSED
    }

    private State state = State.IDLE;


    public void pause() {
        switch (state) {
            case BUFFERING:
            case PLAYING:
                doPause();
                break;
            default:
                //什么都不做
        }
    }

    public void resume() {
        switch (state) {
            case PAUSED:
                doResume();
                break;
            default:
                //什么都不做
        }
    }

    private void doResume() {
        state = State.PLAYING;
        //处理真正的继续播放逻辑
    }

    private void doPause() {
        state = State.PAUSED;
        //处理真正的暂停逻辑
    }
}
