package com.shuyu.gsyvideoplayer.player;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import tv.danmaku.ijk.media.player.misc.IAndroidIO;


/**
 * @author : ling luo
 * @date : 2020/5/18
 */
public class FileAndroidIO implements IAndroidIO {
    private RandomAccessFile mFile;
    private long mCur;

    @Override
    public int open(String url) throws IOException {
        Log.i("FileAndroidIO", "open , url = " + url);
        mFile = new RandomAccessFile(new File(url), "r");
        return 1;
    }

    @Override
    public int read(byte[] buffer, int size) throws IOException {
        if (mFile == null) {
            return -1;
        }
        int read = mFile.read(buffer, 0, size);
        Log.i("FileAndroidIO", "read , size = " + size +
                " , bufferLength = " + buffer.length + " , read = " + read);
        return read;
    }

    @Override
    public long seek(long offset, int whence) throws IOException {
        if (mFile == null) {
            Log.i("FileAndroidIO", "mFile = null");
            return -1;
        }
        if (offset > mFile.length()) {
            Log.i("FileAndroidIO",
                    "seek offset = " + offset + " , > file size : " + mFile.length());
            return -1;
        }
        switch (whence) {
            case 0:
                mCur = offset;
                mFile.seek(mCur);
                return mCur;
            case 1:
                mCur += offset;
                mFile.seek(mCur);
                return mCur;
            case 2:
                mCur = mFile.length() - offset;
                if (mCur < 0) {
                    mCur = 0;
                }
                mFile.seek(mCur);
                return mCur;
            default:
                mCur = offset;
                mFile.seek(mCur);
                break;
        }
        return mFile.length();
    }

    @Override
    public int close() throws IOException {
        Log.i("FileAndroidIO", "close");
        if (mFile != null) {
            mFile.close();
            mFile = null;
        }
        return 1;
    }
}
