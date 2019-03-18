package hongyu315.com.smart2.view;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WeakHandler
{
    private final Handler.Callback mCallback;
    private final ExecHandler mExec;
    private Lock mLock = new ReentrantLock();
    @VisibleForTesting
    final ChainedRef mRunnables = new ChainedRef(this.mLock, null);

    public WeakHandler()
    {
        this.mCallback = null;
        this.mExec = new ExecHandler();
    }

    public WeakHandler(@Nullable Handler.Callback paramCallback)
    {
        this.mCallback = paramCallback;
        this.mExec = new ExecHandler(new WeakReference(paramCallback));
    }

    public WeakHandler(@NonNull Looper paramLooper)
    {
        this.mCallback = null;
        this.mExec = new ExecHandler(paramLooper);
    }

    public WeakHandler(@NonNull Looper paramLooper, @NonNull Handler.Callback paramCallback)
    {
        this.mCallback = paramCallback;
        this.mExec = new ExecHandler(paramLooper, new WeakReference(paramCallback));
    }

    private WeakRunnable wrapRunnable(@NonNull Runnable paramRunnable)
    {
//        if (paramRunnable == null) {
//            throw new NullPointerException("Runnable can't be null");
//        }
//        paramRunnable = new ChainedRef(this.mLock, paramRunnable);
//        this.mRunnables.insertAfter(paramRunnable);
//        return paramRunnable.wrapper;
        return null;
    }

    public final Looper getLooper()
    {
        return this.mExec.getLooper();
    }

    public final boolean hasMessages(int paramInt)
    {
        return this.mExec.hasMessages(paramInt);
    }

    public final boolean hasMessages(int paramInt, Object paramObject)
    {
        return this.mExec.hasMessages(paramInt, paramObject);
    }

    public final boolean post(@NonNull Runnable paramRunnable)
    {
        return this.mExec.post(wrapRunnable(paramRunnable));
    }

    public final boolean postAtFrontOfQueue(Runnable paramRunnable)
    {
        return this.mExec.postAtFrontOfQueue(wrapRunnable(paramRunnable));
    }

    public final boolean postAtTime(@NonNull Runnable paramRunnable, long paramLong)
    {
        return this.mExec.postAtTime(wrapRunnable(paramRunnable), paramLong);
    }

    public final boolean postAtTime(Runnable paramRunnable, Object paramObject, long paramLong)
    {
        return this.mExec.postAtTime(wrapRunnable(paramRunnable), paramObject, paramLong);
    }

    public final boolean postDelayed(Runnable paramRunnable, long paramLong)
    {
        return this.mExec.postDelayed(wrapRunnable(paramRunnable), paramLong);
    }

    public final void removeCallbacks(Runnable paramRunnable)
    {
        paramRunnable = this.mRunnables.remove(paramRunnable);
        if (paramRunnable != null) {
            this.mExec.removeCallbacks(paramRunnable);
        }
    }

    public final void removeCallbacks(Runnable paramRunnable, Object paramObject)
    {
        paramRunnable = this.mRunnables.remove(paramRunnable);
        if (paramRunnable != null) {
            this.mExec.removeCallbacks(paramRunnable, paramObject);
        }
    }

    public final void removeCallbacksAndMessages(Object paramObject)
    {
        this.mExec.removeCallbacksAndMessages(paramObject);
    }

    public final void removeMessages(int paramInt)
    {
        this.mExec.removeMessages(paramInt);
    }

    public final void removeMessages(int paramInt, Object paramObject)
    {
        this.mExec.removeMessages(paramInt, paramObject);
    }

    public final boolean sendEmptyMessage(int paramInt)
    {
        return this.mExec.sendEmptyMessage(paramInt);
    }

    public final boolean sendEmptyMessageAtTime(int paramInt, long paramLong)
    {
        return this.mExec.sendEmptyMessageAtTime(paramInt, paramLong);
    }

    public final boolean sendEmptyMessageDelayed(int paramInt, long paramLong)
    {
        return this.mExec.sendEmptyMessageDelayed(paramInt, paramLong);
    }

    public final boolean sendMessage(Message paramMessage)
    {
        return this.mExec.sendMessage(paramMessage);
    }

    public final boolean sendMessageAtFrontOfQueue(Message paramMessage)
    {
        return this.mExec.sendMessageAtFrontOfQueue(paramMessage);
    }

    public boolean sendMessageAtTime(Message paramMessage, long paramLong)
    {
        return this.mExec.sendMessageAtTime(paramMessage, paramLong);
    }

    public final boolean sendMessageDelayed(Message paramMessage, long paramLong)
    {
        return this.mExec.sendMessageDelayed(paramMessage, paramLong);
    }

    static class ChainedRef
    {
        @NonNull
        Lock lock;
        @Nullable
        ChainedRef next;
        @Nullable
        ChainedRef prev;
        @NonNull
        final Runnable runnable;
        @NonNull
        final WeakHandler.WeakRunnable wrapper;

        public ChainedRef(@NonNull Lock paramLock, @NonNull Runnable paramRunnable)
        {
            this.runnable = paramRunnable;
            this.lock = paramLock;
            this.wrapper = new WeakHandler.WeakRunnable(new WeakReference(paramRunnable), new WeakReference(this));
        }

        public void insertAfter(@NonNull ChainedRef paramChainedRef)
        {
            this.lock.lock();
            try
            {
                if (this.next != null) {
                    this.next.prev = paramChainedRef;
                }
                paramChainedRef.next = this.next;
                this.next = paramChainedRef;
                paramChainedRef.prev = this;
                return;
            }
            finally
            {
                this.lock.unlock();
            }
        }

        public WeakHandler.WeakRunnable remove()
        {
            this.lock.lock();
            try
            {
                if (this.prev != null) {
                    this.prev.next = this.next;
                }
                if (this.next != null) {
                    this.next.prev = this.prev;
                }
                this.prev = null;
                this.next = null;
                return this.wrapper;
            }
            finally
            {
                this.lock.unlock();
            }
        }

        @Nullable
        public WeakHandler.WeakRunnable remove(Runnable paramRunnable)
        {
            this.lock.lock();
            try
            {
                for (ChainedRef localChainedRef = this.next; localChainedRef != null; localChainedRef = localChainedRef.next) {
                    if (localChainedRef.runnable == paramRunnable)
                    {
                        paramRunnable = localChainedRef.remove();
//                        return paramRunnable;
                    }
                }
                return null;
            }
            finally
            {
                this.lock.unlock();
            }
        }
    }

    private static class ExecHandler
            extends Handler
    {
        private final WeakReference<Handler.Callback> mCallback;

        ExecHandler()
        {
            this.mCallback = null;
        }

        ExecHandler(Looper paramLooper)
        {
            super();
            this.mCallback = null;
        }

        ExecHandler(Looper paramLooper, WeakReference<Handler.Callback> paramWeakReference)
        {
            super();
            this.mCallback = paramWeakReference;
        }

        ExecHandler(WeakReference<Handler.Callback> paramWeakReference)
        {
            this.mCallback = paramWeakReference;
        }

        public void handleMessage(@NonNull Message paramMessage)
        {
            if (this.mCallback == null) {}
            Handler.Callback localCallback;
            do
            {
                return;
//                localCallback = (Handler.Callback)this.mCallback.get();
            } while (localCallback == null);
//            localCallback.handleMessage(paramMessage);
        }
    }

    static class WeakRunnable
            implements Runnable
    {
        private final WeakReference<Runnable> mDelegate;
        private final WeakReference<WeakHandler.ChainedRef> mReference;

        WeakRunnable(WeakReference<Runnable> paramWeakReference, WeakReference<WeakHandler.ChainedRef> paramWeakReference1)
        {
            this.mDelegate = paramWeakReference;
            this.mReference = paramWeakReference1;
        }

        public void run()
        {
            Runnable localRunnable = this.mDelegate.get();
            WeakHandler.ChainedRef localChainedRef = this.mReference.get();
            if (localChainedRef != null) {
                localChainedRef.remove();
            }
            if (localRunnable != null) {
                localRunnable.run();
            }
        }
    }
}
