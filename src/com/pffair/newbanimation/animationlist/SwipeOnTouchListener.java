/*
 * Copyright (c) 2013 K–NFB Reading Technology, Inc.
 */

package com.pffair.newbanimation.animationlist;

import android.view.View;

/**
 * An OnTouchListener that should be used when list-view items can be swiped horizontally.
 * @author Anton Spaans on 9/12/13.
 */
public interface SwipeOnTouchListener extends View.OnTouchListener {
    /**
     * @return true if the user is currently swiping a list-item horizontally.
     */
    public boolean isSwiping();
}
