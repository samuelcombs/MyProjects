package com.sam.ptoe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class UnitConv extends Activity implements View.OnClickListener, TextView.OnEditorActionListener, View.OnFocusChangeListener {

    LinearLayout LinTL, LinL, LinTW, LinW, LinTV, LinV, LinTST, LinST;
    TextView L1,L2,W1,W2,V1,V2,ST1,ST2;
    Boolean L,W,V,ST;
    EditText Emm,Ecm,Ein,Eft,Em,Eyd,Ekm,Emi,Emg,Eg,Ekg,Elb,Eoz,Est,Elt,Etonne,Evte,Evtb,Evoz,Evc,Evp,Evq,Evl,Evg,Ef,Ec,Ek,Ekph,Emph;
    String mm,cm,in,ft,m,yd,km,mi,mg,g,kg,lb,oz,st,lt,tonne,vte,vtb,voz,vc,vp,vq,vl,vg,f,c,k,kph,mph;

    static Interpolator easeInOutQuart = PathInterpolatorCompat.create(0.77f, 0f, 0.175f, 1f);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_conv);

        LinTL = findViewById(R.id.LTlength);
        LinL = findViewById(R.id.Llength);
        LinTW = findViewById(R.id.LTweight);
        LinW = findViewById(R.id.Lweight);
        LinTV = findViewById(R.id.LTvolume);
        LinV = findViewById(R.id.Lvolume);
        LinTST = findViewById(R.id.LTst);
        LinST = findViewById(R.id.Lst);
        L1 = findViewById(R.id.L1);
        L2 = findViewById(R.id.L2);
        W1 = findViewById(R.id.W1);
        W2 = findViewById(R.id.W2);
        V1 = findViewById(R.id.V1);
        V2 = findViewById(R.id.V2);
        ST1 = findViewById(R.id.ST1);
        ST2 = findViewById(R.id.ST2);
        Emm = findViewById(R.id.Emm);
        Ecm = findViewById(R.id.Ecm);
        Ein = findViewById(R.id.Ein);
        Eft = findViewById(R.id.Eft);
        Em = findViewById(R.id.Em);
        Eyd = findViewById(R.id.Eyd);
        Ekm = findViewById(R.id.Ekm);
        Emi = findViewById(R.id.Emi);
        Emg = findViewById(R.id.Emg);
        Eg = findViewById(R.id.Eg);
        Ekg = findViewById(R.id.Ekg);
        Elb = findViewById(R.id.Elb);
        Eoz = findViewById(R.id.Eoz);
        Est = findViewById(R.id.Est);
        Elt = findViewById(R.id.Elt);
        Etonne = findViewById(R.id.Etonne);
        Evte = findViewById(R.id.Evte);
        Evtb = findViewById(R.id.Evtb);
        Evoz = findViewById(R.id.Evoz);
        Evc = findViewById(R.id.Evc);
        Evp = findViewById(R.id.Evp);
        Evq = findViewById(R.id.Evq);
        Evl = findViewById(R.id.Evl);
        Evg = findViewById(R.id.Evg);
        Ec = findViewById(R.id.Ec);
        Ef = findViewById(R.id.Ef);
        Ek = findViewById(R.id.Ek);
        Ekph = findViewById(R.id.Ekph);
        Emph = findViewById(R.id.Emph);

        LinTL.setOnClickListener(this);
        LinTW.setOnClickListener(this);
        LinTV.setOnClickListener(this);
        LinTST.setOnClickListener(this);

        Emm.setOnEditorActionListener(this);
        Ecm.setOnEditorActionListener(this);
        Ein.setOnEditorActionListener(this);
        Eft.setOnEditorActionListener(this);
        Em.setOnEditorActionListener(this);
        Eyd.setOnEditorActionListener(this);
        Ekm.setOnEditorActionListener(this);
        Emi.setOnEditorActionListener(this);
        Emg.setOnEditorActionListener(this);
        Eg.setOnEditorActionListener(this);
        Ekg.setOnEditorActionListener(this);
        Elb.setOnEditorActionListener(this);
        Eoz.setOnEditorActionListener(this);
        Est.setOnEditorActionListener(this);
        Elt.setOnEditorActionListener(this);
        Etonne.setOnEditorActionListener(this);
        Evte.setOnEditorActionListener(this);
        Evtb.setOnEditorActionListener(this);
        Evoz.setOnEditorActionListener(this);
        Evc.setOnEditorActionListener(this);
        Evp.setOnEditorActionListener(this);
        Evq.setOnEditorActionListener(this);
        Evl.setOnEditorActionListener(this);
        Evg.setOnEditorActionListener(this);
        Ec.setOnEditorActionListener(this);
        Ef.setOnEditorActionListener(this);
        Ek.setOnEditorActionListener(this);
        Ekph.setOnEditorActionListener(this);
        Emph.setOnEditorActionListener(this);

        Emm.setOnClickListener(this);
        Ecm.setOnClickListener(this);
        Ein.setOnClickListener(this);
        Eft.setOnClickListener(this);
        Em.setOnClickListener(this);
        Eyd.setOnClickListener(this);
        Ekm.setOnClickListener(this);
        Emi.setOnClickListener(this);
        Emg.setOnClickListener(this);
        Eg.setOnClickListener(this);
        Ekg.setOnClickListener(this);
        Elb.setOnClickListener(this);
        Eoz.setOnClickListener(this);
        Est.setOnClickListener(this);
        Elt.setOnClickListener(this);
        Etonne.setOnClickListener(this);
        Evte.setOnClickListener(this);
        Evtb.setOnClickListener(this);
        Evoz.setOnClickListener(this);
        Evc.setOnClickListener(this);
        Evp.setOnClickListener(this);
        Evq.setOnClickListener(this);
        Evl.setOnClickListener(this);
        Evg.setOnClickListener(this);
        Ec.setOnClickListener(this);
        Ef.setOnClickListener(this);
        Ek.setOnClickListener(this);
        Ekph.setOnClickListener(this);
        Emph.setOnClickListener(this);

        Emm.setOnFocusChangeListener(this);
        Ecm.setOnFocusChangeListener(this);
        Ein.setOnFocusChangeListener(this);
        Eft.setOnFocusChangeListener(this);
        Em.setOnFocusChangeListener(this);
        Eyd.setOnFocusChangeListener(this);
        Ekm.setOnFocusChangeListener(this);
        Emi.setOnFocusChangeListener(this);
        Emg.setOnFocusChangeListener(this);
        Eg.setOnFocusChangeListener(this);
        Ekg.setOnFocusChangeListener(this);
        Elb.setOnFocusChangeListener(this);
        Eoz.setOnFocusChangeListener(this);
        Est.setOnFocusChangeListener(this);
        Elt.setOnFocusChangeListener(this);
        Etonne.setOnFocusChangeListener(this);
        Evte.setOnFocusChangeListener(this);
        Evtb.setOnFocusChangeListener(this);
        Evoz.setOnFocusChangeListener(this);
        Evc.setOnFocusChangeListener(this);
        Evp.setOnFocusChangeListener(this);
        Evq.setOnFocusChangeListener(this);
        Evl.setOnFocusChangeListener(this);
        Evg.setOnFocusChangeListener(this);
        Ec.setOnFocusChangeListener(this);
        Ef.setOnFocusChangeListener(this);
        Ek.setOnFocusChangeListener(this);
        Emph.setOnFocusChangeListener(this);
        Ekph.setOnFocusChangeListener(this);

        mm="";cm="";in="";ft="";m="";yd="";km="";mi="";
        mg="";g="";kg="";lb="";oz="";st="";lt="";tonne="";
        vte="";vtb="";voz="";vc="";vp="";vq="";vl="";vg="";
        c="";f="";k="";kph="";mph="";

        L = false;
        W = false;
        V = false;
        ST = false;

        if (!L) LinL.setVisibility(View.GONE);
        if (!W) LinW.setVisibility(View.GONE);
        if (!V) LinV.setVisibility(View.GONE);
        if (!ST) LinST.setVisibility(View.GONE);
    }
    public void onResume() {
        super.onResume();

        Emm.setText(mm);
        Ecm.setText(cm);
        Ein.setText(in);
        Eft.setText(ft);
        Em.setText(m);
        Eyd.setText(yd);
        Ekm.setText(km);
        Emi.setText(mi);

        Emg.setText(mg);
        Eg.setText(g);
        Ekg.setText(kg);
        Elb.setText(lb);
        Eoz.setText(oz);
        Est.setText(st);
        Elt.setText(lt);
        Etonne.setText(tonne);

        Evte.setText(vte);
        Evtb.setText(vtb);
        Evoz.setText(voz);
        Evc.setText(vc);
        Evp.setText(vp);
        Evq.setText(vq);
        Evl.setText(vl);
        Evg.setText(vg);

        Ec.setText(c);
        Ef.setText(f);
        Ek.setText(k);
        Ekph.setText(kph);
        Emph.setText(mph);

        calculateAndDisplay();
    }
    @Override
    public void onClick(View v) {

        mm="";cm="";in="";ft="";m="";yd="";km="";mi="";
        mg="";g="";kg="";lb="";oz="";st="";lt="";tonne="";
        vte="";vtb="";voz="";vc="";vp="";vq="";vl="";vg="";
        c="";f="";k="";kph="";mph="";

        Emm.setText(mm);
        Ecm.setText(cm);
        Ein.setText(in);
        Eft.setText(ft);
        Em.setText(m);
        Eyd.setText(yd);
        Ekm.setText(km);
        Emi.setText(mi);

        Emg.setText(mg);
        Eg.setText(g);
        Ekg.setText(kg);
        Elb.setText(lb);
        Eoz.setText(oz);
        Est.setText(st);
        Elt.setText(lt);
        Etonne.setText(tonne);

        Evte.setText(vte);
        Evtb.setText(vtb);
        Evoz.setText(voz);
        Evc.setText(vc);
        Evp.setText(vp);
        Evq.setText(vq);
        Evl.setText(vl);
        Evg.setText(vg);

        Ec.setText(c);
        Ef.setText(f);
        Ek.setText(k);
        Ekph.setText(kph);
        Emph.setText(mph);

        switch (v.getId()) {
            case R.id.LTlength:
                if (W) {
                    collapse(LinW);
                    W = false;
                }
                if (V) {
                    collapse(LinV);
                    V = false;
                }
                if (ST) {
                    collapse(LinST);
                    ST = false;
                }
                if (!L) {
                    expand(LinL);
                    L = true;
                    L1.setText("-");
                    L2.setText("-");
                } else {
                    collapse(LinL);
                    L = false;
                    L1.setText("+");
                    L2.setText("+");
                }
                break;
            case R.id.LTweight:
                if (L) {
                    collapse(LinL);
                    L = false;
                }
                if (V) {
                    collapse(LinV);
                    V = false;
                }
                if (ST) {
                    collapse(LinST);
                    ST = false;
                }
                if (!W) {
                    expand(LinW);
                    W = true;
                    W1.setText("-");
                    W2.setText("-");
                } else {
                    collapse(LinW);
                    W = false;
                    W1.setText("+");
                    W2.setText("+");
                }
                break;
            case R.id.LTvolume:
                if (L) {
                    collapse(LinL);
                    L = false;
                }
                if (W) {
                    collapse(LinW);
                    W = false;
                }
                if (ST) {
                    collapse(LinST);
                    ST = false;
                }
                if (!V) {
                    expand(LinV);
                    V = true;
                    V1.setText("-");
                    V2.setText("-");
                } else {
                    collapse(LinV);
                    V = false;
                    V1.setText("+");
                    V2.setText("+");
                }
                break;
            case R.id.LTst:
                if (L) {
                    collapse(LinL);
                    L = false;
                }
                if (W) {
                    collapse(LinW);
                    W = false;
                }
                if (V) {
                    collapse(LinV);
                    V = false;
                }
                if (!ST) {
                    expand(LinST);
                    ST = true;
                    ST1.setText("-");
                    ST2.setText("-");
                } else {
                    collapse(LinST);
                    ST = false;
                    ST1.setText("+");
                    ST2.setText("+");
                }
                break;
            case R.id.Emm:
                clearAll();
                break;
            case R.id.Ecm:
                clearAll();
                break;
            case R.id.Ein:
                clearAll();
                break;
            case R.id.Eft:
                clearAll();
                break;
            case R.id.Em:
                clearAll();
                break;
            case R.id.Eyd:
                clearAll();
                break;
            case R.id.Ekm:
                clearAll();
                break;
            case R.id.Emi:
                clearAll();
                break;
            case R.id.Emg:
                clearAll();
                break;
            case R.id.Eg:
                clearAll();
                break;
            case R.id.Ekg:
                clearAll();
                break;
            case R.id.Elb:
                clearAll();
                break;
            case R.id.Eoz:
                clearAll();
                break;
            case R.id.Est:
                clearAll();
                break;
            case R.id.Elt:
                clearAll();
                break;
            case R.id.Etonne:
                clearAll();
                break;
            case R.id.Evte:
                clearAll();
                break;
            case R.id.Evtb:
                clearAll();
                break;
            case R.id.Evoz:
                clearAll();
                break;
            case R.id.Evc:
                clearAll();
                break;
            case R.id.Evp:
                clearAll();
                break;
            case R.id.Evq:
                clearAll();
                break;
            case R.id.Evl:
                clearAll();
                break;
            case R.id.Evg:
                clearAll();
                break;
            case R.id.Ec:
                clearAll();
                break;
            case R.id.Ef:
                clearAll();
                break;
            case R.id.Ek:
                clearAll();
                break;
            case R.id.Ekph:
                clearAll();
                break;
            case R.id.Emph:
                clearAll();
                break;
        }
    }

    public void clearAll(){
        mm="";cm="";in="";ft="";m="";yd="";km="";mi="";
        mg="";g="";kg="";lb="";oz="";st="";lt="";tonne="";
        vte="";vtb="";voz="";vc="";vp="";vq="";vl="";vg="";
        c="";f="";k="";kph="";mph="";

        Emm.setText(mm);
        Ecm.setText(cm);
        Ein.setText(in);
        Eft.setText(ft);
        Em.setText(m);
        Eyd.setText(yd);
        Ekm.setText(km);
        Emi.setText(mi);

        Emg.setText(mg);
        Eg.setText(g);
        Ekg.setText(kg);
        Elb.setText(lb);
        Eoz.setText(oz);
        Est.setText(st);
        Elt.setText(lt);
        Etonne.setText(tonne);

        Evte.setText(vte);
        Evtb.setText(vtb);
        Evoz.setText(voz);
        Evc.setText(vc);
        Evp.setText(vp);
        Evq.setText(vq);
        Evl.setText(vl);
        Evg.setText(vg);

        Ec.setText(c);
        Ef.setText(f);
        Ek.setText(k);
        Ekph.setText(kph);
        Emph.setText(mph);
    }

    @Override
    public void onFocusChange (View v, boolean hasFocus){
        clearAll();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED ) {
            calculateAndDisplay();
        }
        return false;
    }

    public void calculateAndDisplay() {

        // get the edittext;

        if (L) {
            mm = Emm.getText().toString();
            cm = Ecm.getText().toString();
            in = Ein.getText().toString();
            ft = Eft.getText().toString();
            m = Em.getText().toString();
            yd = Eyd.getText().toString();
            km = Ekm.getText().toString();
            mi = Emi.getText().toString();

            double mmf,cmf,inf,ftf,mf,ydf,kmf,mif;

            if (mm.equals("")) mmf = 0;
            else mmf = Double.parseDouble(mm);
            if (cm.equals("")) cmf = 0;
            else cmf = Double.parseDouble(cm);
            if (in.equals("")) inf = 0;
            else inf = Double.parseDouble(in);
            if (ft.equals("")) ftf = 0;
            else ftf = Double.parseDouble(ft);
            if (m.equals("")) mf = 0;
            else mf = Double.parseDouble(m);
            if (yd.equals("")) ydf = 0;
            else ydf = Double.parseDouble(yd);
            if (km.equals("")) kmf = 0;
            else kmf = Double.parseDouble(km);
            if (mi.equals("")) mif = 0;
            else mif = Double.parseDouble(mi);

            if(mmf!=0){
                cmf=mmf/10;
                inf=mmf/25.4;
                ftf=mmf/304.8;
                mf=mmf/1000;
                ydf=mmf/914.4;
                kmf=mmf/1000000;
                mif=kmf/1.609344;
            } else if (cmf!=0){
                mmf=cmf*10;
                inf=cmf*0.3937008;
                ftf=cmf*0.032808;
                mf=cmf/100;
                ydf=cmf*0.01093613;
                kmf=cmf/100000;
                mif=kmf/1.609344;
            } else if (inf!=0){
                mmf=inf/0.03937008;
                cmf=inf/0.3937008;
                ftf=inf/12;
                mf=inf/39.37008;
                ydf=inf/36;
                kmf=mf/1000;
                mif=ftf/5280;
            } else if (ftf!=0){
                mmf=ftf/0.00328084;
                cmf=mmf/10;
                inf=ftf*12;
                mf=inf/39.37008;
                ydf=ftf/3;
                kmf=mf/1000;
                mif=ftf/5280;
            } else if (mf!=0){
                mmf=mf*1000;
                cmf=mf*100;
                inf=cmf*0.3937008;
                ftf=cmf*0.032808;
                ydf=cmf*0.01093613;
                kmf=mf/1000;
                mif=kmf/1.609344;
            } else if (ydf!=0) {
                ftf=ydf*3;
                inf=ftf*12;
                mmf=ftf/0.00328084;
                cmf=mmf/10;
                mf=ydf/1.0936;
                kmf=mf/1000;
                mif=ydf/1760;
            } else if (kmf!=0) {
                mmf=kmf*1000000;
                cmf=mmf/10;
                inf=cmf*0.3937008;
                ftf=cmf*0.032808;
                ydf=cmf*0.01093613;
                mf=kmf*1000;
                mif=kmf/1.609344;
            } else if (mif!=0) {
                ydf=mif*1760;
                ftf=mif*5280;
                inf=ftf*12;
                kmf=mif*1.609344;
                cmf=kmf*100000;
                mmf=cmf*10;
                mf=kmf*1000;
            }

            mmf = Math.round(mmf*100000000.0)/100000000.0;
            cmf = Math.round(cmf*100000000.0)/100000000.0;
            inf = Math.round(inf*100000000.0)/100000000.0;
            ftf = Math.round(ftf*100000000.0)/100000000.0;
            mf = Math.round(mf*100000000.0)/100000000.0;
            ydf = Math.round(ydf*100000000.0)/100000000.0;
            kmf = Math.round(kmf*100000000.0)/100000000.0;
            mif = Math.round(mif*100000000.0)/100000000.0;

            mm = Double.toString(mmf);
            cm = Double.toString(cmf);
            in = Double.toString(inf);
            ft = Double.toString(ftf);
            m = Double.toString(mf);
            yd = Double.toString(ydf);
            km = Double.toString(kmf);
            mi = Double.toString(mif);

            Emm.setText(mm);
            Ecm.setText(cm);
            Ein.setText(in);
            Eft.setText(ft);
            Em.setText(m);
            Eyd.setText(yd);
            Ekm.setText(km);
            Emi.setText(mi);
        }

        if (W) {
            mg = Emg.getText().toString();
            g = Eg.getText().toString();
            kg = Ekg.getText().toString();
            lb = Elb.getText().toString();
            oz = Eoz.getText().toString();
            st = Est.getText().toString();
            lt = Elt.getText().toString();
            tonne = Etonne.getText().toString();

            double mgf,gf,kgf,lbf,ozf,stf,ltf,tonf;

            if (mg.equals("")) mgf = 0;
            else mgf = Double.parseDouble(mg);
            if (g.equals("")) gf = 0;
            else gf = Double.parseDouble(g);
            if (kg.equals("")) kgf = 0;
            else kgf = Double.parseDouble(kg);
            if (lb.equals("")) lbf = 0;
            else lbf = Double.parseDouble(lb);
            if (oz.equals("")) ozf = 0;
            else ozf = Double.parseDouble(oz);
            if (st.equals("")) stf = 0;
            else stf = Double.parseDouble(st);
            if (lt.equals("")) ltf = 0;
            else ltf = Double.parseDouble(lt);
            if (tonne.equals("")) tonf = 0;
            else tonf = Double.parseDouble(tonne);

            if (mgf!=0) {
                gf=mgf/1000;
                kgf=gf/1000;
                tonf=kgf/1000;
                ozf=gf*0.035274;
                lbf=ozf/16;
                stf=lbf/2000;
                ltf=lbf/2240;
            } else if (gf!=0) {
                mgf=gf*1000;
                kgf=gf/1000;
                tonf=kgf/1000;
                ozf=gf*0.035274;
                lbf=ozf/16;
                stf=lbf/2000;
                ltf=lbf/2240;
            } else if (kgf!=0) {
                gf=kgf*1000;
                mgf=gf*1000;
                tonf=kgf/1000;
                ozf=gf*0.035274;
                lbf=ozf/16;
                stf=lbf/2000;
                ltf=lbf/2240;
            } else if (lbf!=0) {
                ozf=lbf*16;
                stf=lbf/2000;
                ltf=lbf/2240;
                gf=ozf/0.035274;
                mgf=gf*1000;
                kgf=gf/1000;
                tonf=kgf/1000;
            } else if (ozf!=0) {
                lbf=ozf/16;
                stf=lbf/2000;
                ltf=lbf/2240;
                gf=ozf/0.035274;
                mgf=gf*1000;
                kgf=gf/1000;
                tonf=kgf/1000;
            } else if (stf!=0) {
                lbf=stf*2000;
                ozf=lbf*16;
                ltf=lbf/2240;
                gf=ozf/0.035274;
                mgf=gf*1000;
                kgf=gf/1000;
                tonf=kgf/1000;
            } else if (ltf!=0) {
                lbf=ltf*2240;
                ozf=lbf*16;
                stf=lbf/2000;
                gf=ozf/0.035274;
                mgf=gf*1000;
                kgf=gf/1000;
                tonf=kgf/1000;
            } else if (tonf!=0) {
                kgf=tonf*1000;
                gf=kgf*1000;
                mgf=gf*1000;
                ozf=gf*0.035274;
                lbf=ozf/16;
                stf=lbf/2000;
                ltf=lbf/2240;
            }

            mgf = Math.round(mgf*10000000.0)/10000000.0;
            gf = Math.round(gf*10000000.0)/10000000.0;
            kgf = Math.round(kgf*10000000.0)/10000000.0;
            lbf = Math.round(lbf*10000000.0)/10000000.0;
            ozf = Math.round(ozf*10000000.0)/10000000.0;
            stf = Math.round(stf*10000000.0)/10000000.0;
            ltf = Math.round(ltf*10000000.0)/10000000.0;
            tonf = Math.round(tonf*10000000.0)/10000000.0;

            mg = Double.toString(mgf);
            g = Double.toString(gf);
            kg = Double.toString(kgf);
            lb = Double.toString(lbf);
            oz = Double.toString(ozf);
            st = Double.toString(stf);
            lt = Double.toString(ltf);
            tonne = Double.toString(tonf);

            Emg.setText(mg);
            Eg.setText(g);
            Ekg.setText(kg);
            Elb.setText(lb);
            Eoz.setText(oz);
            Est.setText(st);
            Elt.setText(lt);
            Etonne.setText(tonne);
        }

        if (V) {
            vte = Evte.getText().toString();
            vtb = Evtb.getText().toString();
            voz = Evoz.getText().toString();
            vc = Evc.getText().toString();
            vp = Evp.getText().toString();
            vq = Evq.getText().toString();
            vl = Evl.getText().toString();
            vg = Evg.getText().toString();

            double vtef,vtbf,vozf,vcf,vpf,vqf,vlf,vgf;

            if (vte.equals("")) vtef = 0;
            else vtef = Double.parseDouble(vte);
            if (vtb.equals("")) vtbf = 0;
            else vtbf = Double.parseDouble(vtb);
            if (voz.equals("")) vozf = 0;
            else vozf = Double.parseDouble(voz);
            if (vc.equals("")) vcf = 0;
            else vcf = Double.parseDouble(vc);
            if (vp.equals("")) vpf = 0;
            else vpf = Double.parseDouble(vp);
            if (vq.equals("")) vqf = 0;
            else vqf = Double.parseDouble(vq);
            if (vl.equals("")) vlf = 0;
            else vlf = Double.parseDouble(vl);
            if (vg.equals("")) vgf = 0;
            else vgf = Double.parseDouble(vg);

            if (vtef!=0) {
                vtbf=vtef/3;
                vozf=vtbf/2;
                vcf=vozf/8;
                vqf=vtbf/64;
                vpf=vqf*2;
                vlf=vtbf/67.628;
                vgf=vqf/4;
            } else if (vtbf!=0) {
                vtef=vtbf*3;
                vozf=vtbf/2;
                vcf=vozf/8;
                vqf=vtbf/64;
                vpf=vqf*2;
                vlf=vtbf/67.628;
                vgf=vqf/4;
            } else if (vozf!=0) {
                vtbf=vozf*2;
                vtef=vtbf*3;
                vcf=vozf/8;
                vpf=vozf*0.0625;
                vqf=vtbf/64;
                vlf=vtbf/67.628;
                vgf=vqf/4;
            } else if (vcf!=0) {
                vozf=vcf*8;
                vtbf=vozf*2;
                vtef=vtbf*3;
                vpf=vozf*0.0625;
                vqf=vtbf/64;
                vlf=vtbf/67.628;
                vgf=vqf/4;
            } else if (vpf!=0) {
                vqf=vpf/2;
                vtbf=vqf*64;
                vtef=vtbf*3;
                vozf=vtbf/2;
                vcf=vozf/8;
                vlf=vtbf/67.628;
                vgf=vqf/4;
            } else if (vqf!=0) {
                vtbf=vqf*64;
                vtef=vtbf*3;
                vozf=vtbf/2;
                vcf=vozf/8;
                vpf=vqf*2;
                vlf=vtbf/67.628;
                vgf=vqf/4;
            } else if (vlf!=0) {
                vtbf=vlf*67.628;
                vtef=vtbf*3;
                vozf=vtbf/2;
                vcf=vozf/8;
                vqf=vtbf/64;
                vpf=vqf*2;
                vgf=vqf/4;
            } else if (vgf!=0) {
                vqf=vgf*4;
                vpf=vqf*2;
                vtbf=vqf*64;
                vtef=vtbf*3;
                vozf=vtbf/2;
                vcf=vozf/8;
                vlf=vtbf/67.628;
            }

            vtef = Math.round(vtef*10000000.0)/10000000.0;
            vtbf = Math.round(vtbf*10000000.0)/10000000.0;
            vozf = Math.round(vozf*10000000.0)/10000000.0;
            vcf = Math.round(vcf*10000000.0)/10000000.0;
            vpf = Math.round(vpf*10000000.0)/10000000.0;
            vqf = Math.round(vqf*10000000.0)/10000000.0;
            vlf = Math.round(vlf*10000000.0)/10000000.0;
            vgf = Math.round(vgf*10000000.0)/10000000.0;

            vte = Double.toString(vtef);
            vtb = Double.toString(vtbf);
            voz = Double.toString(vozf);
            vc = Double.toString(vcf);
            vp = Double.toString(vpf);
            vq = Double.toString(vqf);
            vl = Double.toString(vlf);
            vg = Double.toString(vgf);

            Evte.setText(vte);
            Evtb.setText(vtb);
            Evoz.setText(voz);
            Evc.setText(vc);
            Evp.setText(vp);
            Evq.setText(vq);
            Evl.setText(vl);
            Evg.setText(vg);
        }

        if (ST) {

            c = Ec.getText().toString();
            f = Ef.getText().toString();
            k = Ek.getText().toString();
            kph = Ekph.getText().toString();
            mph = Emph.getText().toString();

            double cf,ff,kf,kphf,mphf;

            if (c.equals("")) cf = 0;
            else cf = Double.parseDouble(c);
            if (f.equals("")) ff = 0;
            else ff = Double.parseDouble(f);
            if (k.equals("")) kf = 0;
            else kf = Double.parseDouble(k);
            if (kph.equals("")) kphf = 0;
            else kphf = Double.parseDouble(kph);
            if (mph.equals("")) mphf = 0;
            else mphf = Double.parseDouble(mph);

            if (cf!=0) {
                ff=cf*9/5+32;
                kf=cf+273.15;
            } else if (ff!=0) {
                cf=(ff-32)*5/9;
                kf=(ff+459.67)*5/9;
            } else if (kf!=0) {
                ff=kf*9/5-459.67;
                cf=kf-273.15;
            }

            if (kphf!=0) {
                mphf=kphf*0.6213711922;
            } else if (mphf!=0) {
                kphf=mphf*1.609344;
            }

            cf = Math.round(cf*10000000.0)/10000000.0;
            ff = Math.round(ff*10000000.0)/10000000.0;
            kf = Math.round(kf*10000000.0)/10000000.0;
            kphf = Math.round(kphf*10000000.0)/10000000.0;
            mphf = Math.round(mphf*10000000.0)/10000000.0;

            if (cf!=0 || ff!=0 || kf!=0) {
                c = Double.toString(cf);
                f = Double.toString(ff);
                k = Double.toString(kf);
            }
            if (kphf!=0 || mphf!=0) {
                kph = Double.toString(kphf);
                mph = Double.toString(mphf);
            }

            Ec.setText(c);
            Ef.setText(f);
            Ek.setText(k);
            Ekph.setText(kph);
            Emph.setText(mph);
        }


//        NumberFormat nm = NumberFormat.getNumberInstance();
//
//        if (mmf!=0)Emm.setText(nm.format(mmf));
//        if (cmf!=0)Ecm.setText(nm.format(cmf));
//        if (inf!=0)Ein.setText(nm.format(inf));
//        if (ftf!=0)Eft.setText(nm.format(ftf));
//        if (mf!=0)Em.setText(nm.format(mf));
//        if (ydf!=0)Eyd.setText(nm.format(ydf));
//        if (kmf!=0)Ekm.setText(nm.format(kmf));
//        if (mif!=0)Emi.setText(nm.format(mif));

//        BigDecimal mmf,cmf,inf,ftf,mf,ydf,kmf,mif,mgf,gf,kgf,lbf;
//
//        if (mm.equals("")) mmf = new BigDecimal(0);
//        else mmf = new BigDecimal(String.valueOf(mm));
//        if (cm.equals("")) cmf = new BigDecimal(0);
//        else cmf = new BigDecimal(String.valueOf(cm));
//        if (in.equals("")) inf = new BigDecimal(0);
//        else inf = new BigDecimal(String.valueOf(in));
//        if (ft.equals("")) ftf = new BigDecimal(0);
//        else ftf = new BigDecimal(String.valueOf(ft));
//        if (m.equals("")) mf = new BigDecimal(0);
//        else mf = new BigDecimal(String.valueOf(m));
//        if (yd.equals("")) ydf = new BigDecimal(0);
//        else ydf = new BigDecimal(String.valueOf(yd));
//        if (km.equals("")) kmf = new BigDecimal(0);
//        else kmf = new BigDecimal(String.valueOf(km));
//        if (mi.equals("")) mif = new BigDecimal(0);
//        else mif = new BigDecimal(String.valueOf(mi));
//        if (mg.equals("")) mgf = new BigDecimal(0);
//        else mgf = new BigDecimal(String.valueOf(mg));
//        if (g.equals("")) gf = new BigDecimal(0);
//        else gf = new BigDecimal(String.valueOf(g));
//        if (kg.equals("")) kgf = new BigDecimal(0);
//        else kgf = new BigDecimal(String.valueOf(kg));
//        if (lb.equals("")) lbf = new BigDecimal(0);
//        else lbf = new BigDecimal(String.valueOf(lb));
//
//        BigDecimal zero = new BigDecimal(0);
//        BigDecimal cmBD = new BigDecimal(10);
//        BigDecimal inBD = new BigDecimal(25.4);
//        BigDecimal ftBD = new BigDecimal(304.8);
//        BigDecimal mBD = new BigDecimal(1000);
//        BigDecimal ydBD = new BigDecimal(914.4);
//        BigDecimal kmBD = new BigDecimal(1000000);
//        BigDecimal miBD = new BigDecimal(1.609);
//
//        if(mmf.compareTo(zero)!=0){
//            cmf=mmf.divide(cmBD, 10, RoundingMode.UP);
//            inf=mmf.divide(inBD, 10, RoundingMode.UP);
//            ftf=mmf.divide(ftBD, 10, RoundingMode.UP);
//            mf=mmf.divide(mBD, 10, RoundingMode.UP);
//            ydf=mmf.divide(ydBD,10, RoundingMode.UP);
//            kmf=mmf.divide(kmBD, 10, RoundingMode.UP);
//            mif=kmf.divide(miBD, 10, RoundingMode.UP);
//        }

//        mm = mmf.toString();
//        cm = cmf.toString();
//        in = inf.toString();
//        ft = ftf.toString();
//        m = mf.toString();
//        yd = ydf.toString();
//        km = kmf.toString();
//        mi = mif.toString();

        //DecimalFormat f = new DecimalFormat("##.0000000000");

//        Emm.setText(String.format(Locale.US, "%.6f", mmf));
//        Ecm.setText(String.format(cm, f));
//        Ein.setText(String.format(in, f));
//        Eft.setText(String.format(ft, f));
//        Em.setText(String.format(m, f));
//        Eyd.setText(String.format(yd, f));
//        Ekm.setText(String.format(km, f));
//        Emi.setText(String.format(mi, f));

//        NumberFormat nm = NumberFormat.getNumberInstance();
//
//        Emm.setText(nm.format(mmf));
//        Ecm.setText(nm.format(cmf));
//        Ein.setText(nm.format(inf));
//        Eft.setText(nm.format(ftf));
//        Em.setText(nm.format(mf));
//        Eyd.setText(nm.format(ydf));
//        Ekm.setText(nm.format(kmf));
//        Emi.setText(nm.format(mif));

//        if (mm.equals("")) mmf = new BigDecimal(0);
//        else mmf = new BigDecimal(mm);
//        if (cm.equals("")) cmf = new BigDecimal(0);
//        else cmf = new BigDecimal(cm);
//        if (in.equals("")) inf = new BigDecimal(0);
//        else inf = new BigDecimal(in);
//        if (ft.equals("")) ftf = new BigDecimal(0);
//        else ftf = new BigDecimal(ft);
//        if (m.equals("")) mf = new BigDecimal(0);
//        else mf = new BigDecimal(m);
//        if (yd.equals("")) ydf = new BigDecimal(0);
//        else ydf = new BigDecimal(yd);
//        if (km.equals("")) kmf = new BigDecimal(0);
//        else kmf = new BigDecimal(km);
//        if (mi.equals("")) mif = new BigDecimal(0);
//        else mif = new BigDecimal(mi);
//        if (mg.equals("")) mgf = new BigDecimal(0);
//        else mgf = new BigDecimal(mg);
//        if (g.equals("")) gf = new BigDecimal(0);
//        else gf = new BigDecimal(g);
//        if (kg.equals("")) kgf = new BigDecimal(0);
//        else kgf = new BigDecimal(kg);
//        if (lb.equals("")) lbf = new BigDecimal(0);
//        else lbf = new BigDecimal(lb);
//
//        BigDecimal zero = new BigDecimal(0);
//        BigDecimal ten = new BigDecimal(10);
//        BigDecimal inBD = new BigDecimal(25.4);
//        BigDecimal ftBD = new BigDecimal(304.8);
//        BigDecimal mBD = new BigDecimal(1000);
//        BigDecimal ydBD = new BigDecimal(914.4);
//        BigDecimal kmBD = new BigDecimal(1000000);
//        BigDecimal miBD = new BigDecimal(1.609);
//
//        // calculate
//        if(mmf.compareTo(zero) != 0){
//            cmf=mmf.divide(ten);
//            inf=mmf.divide(inBD);
//            ftf=mmf.divide(ftBD);
//            mf=mmf.divide(mBD);
//            ydf=mmf.divide(ydBD);
//            kmf=mmf.divide(kmBD);
//            mif=kmf.divide(miBD);
//        }
//
//        NumberFormat nm = NumberFormat.getInstance();
//
//        if (mmf.compareTo(zero) != 0)Emm.setText(nm.format(mmf));
//        if (cmf.compareTo(zero) != 0)Ecm.setText(nm.format(cmf));
//        if (inf.compareTo(zero) != 0)Ein.setText(nm.format(inf));
//        if (ftf.compareTo(zero) != 0)Eft.setText(nm.format(ftf));
//        if (mf.compareTo(zero) != 0)Em.setText(nm.format(mf));
//        if (ydf.compareTo(zero) != 0)Eyd.setText(nm.format(ydf));
//        if (kmf.compareTo(zero) != 0)Ekm.setText(nm.format(kmf));
//        if (mif.compareTo(zero) != 0)Emi.setText(nm.format(mif));


        // display the other results with formatting
        //NumberFormat currency = NumberFormat.getCurrencyInstance();

        //NumberFormat nm = NumberFormat.getNumberInstance();

        //totalTextView.setText(currency.format(totalAmount));

        //NumberFormat percent = NumberFormat.getPercentInstance();
        //percentTextView.setText(percent.format(tipPercent));
    }

    public static Animation expand(final View view) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) view.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = view.getMeasuredHeight();
        // Older versions of android (pre API 21) cancel animations for views with a height of 0 so use 1 instead.
        //if (Build.VERSION.SDK_INT < 21) {
            view.getLayoutParams().height = 1;
        //}
        view.setVisibility(View.VISIBLE);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                view.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);

                view.requestLayout();
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setInterpolator(easeInOutQuart);
        animation.setDuration(computeDurationFromHeight(view));
        view.startAnimation(animation);
        return animation;
    }

    public static Animation collapse(final View view) {
        final int initialHeight = view.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setInterpolator(easeInOutQuart);
        int durationMillis = computeDurationFromHeight(view);
        a.setDuration(durationMillis);
        view.startAnimation(a);
        return a;
    }

    private static int computeDurationFromHeight(View view) {
        // 1dp/ms * multiplier
        return (int) (view.getMeasuredHeight() / view.getContext().getResources().getDisplayMetrics().density);
    }
}
