//
//  NSTStreetViewManager.java
//  react-native-streetview
//
//  Created by Amit Palomo on 26/04/2017.
//  Copyright © 2017 Nester.co.il.
//

package co.il.nester.android.react.streetview;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nullable;

public class NSTStreetViewManager extends SimpleViewManager<NSTStreetView> {

    public static final String REACT_CLASS = "NSTStreetView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected NSTStreetView createViewInstance(ThemedReactContext themedReactContext) {
        return new NSTStreetView(themedReactContext);
    }

    ///////////////////////////////
    // Modified by SL 2020-07-04
    @ReactProp(name = "streetNamesHidden", defaultBoolean = false)
    public void setStreetNamesHidden(NSTStreetView view, boolean streetNamesHidden) {
        view.enableStreetNames = !streetNamesHidden;
    }
    ///////////////////////////////

    @ReactProp(name = "allGesturesEnabled", defaultBoolean = false)
    public void setAllGesturesEnabled(NSTStreetView view, boolean allGesturesEnabled) {
        view.setAllGesturesEnabled(allGesturesEnabled);
    }

    @ReactProp(name = "coordinate")
    public void setCoordinate(NSTStreetView view, ReadableMap coordinate) {
        view.setCoordinate(coordinate);
    }
    @ReactProp(name = "pov")
        public void setPov(NSTStreetView view, ReadableMap pov) {
            view.setPov(pov);
        }

    @Override
    public @Nullable
    Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                NSTStreetViewEvent.eventNameForType(NSTStreetViewEvent.ON_ERROR), MapBuilder.of("registrationName", NSTStreetViewEvent.eventNameForType(NSTStreetViewEvent.ON_ERROR)),
                NSTStreetViewEvent.eventNameForType(NSTStreetViewEvent.ON_SUCCESS), MapBuilder.of("registrationName", NSTStreetViewEvent.eventNameForType(NSTStreetViewEvent.ON_SUCCESS))
                
                ////////////////////////////////                
                // Modified by SL 2020-07-05
                NSTStreetViewEvent.eventNameForType(NSTStreetViewEvent.ON_DID_MOVE_CAMERA), MapBuilder.of("registrationName", "onDidMoveCamera"),
                ////////////////////////////////
        );
    }
}
