//
//  NSTStreetView.m
//  NSTStreetview
//
//  Copyright © 2019 Nester. All rights reserved.
//

#import "NSTStreetView.h"

@implementation NSTStreetView

#pragma mark GMSPanoramaViewDelegate
- (void)panoramaView:(GMSPanoramaView *)view error:(NSError *)error onMoveNearCoordinate:(CLLocationCoordinate2D)coordinate {
    if(_onError) {
        NSNumber *lat = [[NSNumber alloc] initWithDouble:coordinate.latitude];
        NSNumber *lng = [[NSNumber alloc] initWithDouble:coordinate.longitude];
        NSDictionary *coord = @{@"latitude":lat,@"longitude":lng};
        _onError(@{@"coordinate":coord});
    }
}

- (void)panoramaViewDidFinishRendering:(GMSPanoramaView *)panoramaView
{
    if(_onSuccess) {
        NSNumber *lat = [[NSNumber alloc] initWithDouble:panoramaView.panorama.coordinate.latitude];
        NSNumber *lng = [[NSNumber alloc] initWithDouble:panoramaView.panorama.coordinate.longitude];
        NSDictionary *coord = @{@"latitude":lat,@"longitude":lng};
        _onSuccess(@{@"coordinate":coord});
    }
}

///////////////////////////
// Modified by SL 2020-07-05
- (void)panoramaView:(GMSPanoramaView *)panoramaView didMoveCamera:(GMSPanoramaCamera *)camera {
	if (_onDidMoveCamera) {
		NSDictionary *cameraDict = @{
			// @"orientation": @{
			// 		@"heading":@(camera.orientation.heading),
			// 		@"pitch":@(camera.orientation.pitch),
			// },
			@"heading":@(camera.orientation.heading),
			@"pitch":@(camera.orientation.pitch),
			@"FOV":@(camera.FOV),
			@"zoom":@(camera.zoom)
		};
		
		_onDidMoveCamera(@{@"camera":cameraDict});
	}
}

- (void)panoramaViewDidStartRendering:(GMSPanoramaView *)panoramaView {
	// NOTE: This is sometimes called just when panning around, so do not black out view here
}
///////////////////////////

@end
