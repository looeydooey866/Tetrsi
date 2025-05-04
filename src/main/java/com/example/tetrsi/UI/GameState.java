package com.example.tetrsi.UI;

public class GameState {
    //this is the hard part, so now what you need to do is set a maximum height and width for the board
    //and then fit it into there, so basically my idea is that we get the board to fit first
    //and then we have to fit the ui
    //soo first of all, the ui must fit alongside the board so that means that the uiw ill be dependent on the
    //board's length,
    // the hold should take up about like 80% of the side?
    // and the ui's width is dependent on the ui's height
    // r=ux/uy
    // f=0.8
    // uy=f*bx
    // hold same width as the ui. height depends on width
    // hx = ux
    // hy = m*hx
    // so that means we just have to fit the whole state in, scaling its x and y
    // sx = hx + bx + ux
    // sy = hy + by + uy
    // if sx > lx then z = lx/sx
    // if sy > ly then z = ly/sy
    // everything times with z
    // it should be offset from the what, the center from the board?

}
