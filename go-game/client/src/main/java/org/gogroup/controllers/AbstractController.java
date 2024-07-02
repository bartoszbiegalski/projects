package org.gogroup.controllers;

import org.gogroup.WindowManager;

public abstract class AbstractController {

    protected WindowManager windowManager;

    public void setWindowManager(WindowManager windowManager)
    {
          this.windowManager = windowManager;
    }

}
