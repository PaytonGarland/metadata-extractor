/*
 * Copyright 2002-2017 Drew Noakes
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * More information about this project is available at:
 *
 *    https://drewnoakes.com/code/exif/
 *    https://github.com/drewnoakes/metadata-extractor
 */
package com.drew.metadata.xmp;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.impl.XMPMetaImpl;
import com.adobe.xmp.properties.XMPPropertyInfo;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Wraps an instance of Adobe's {@link XMPMeta} object, which holds XMP data.
 * <p />
 * XMP uses a namespace and path format for identifying values, which does not map to metadata-extractor's
 * integer based tag identifiers. Therefore, XMP data is extracted and exposed via {@link XmpDirectory#getXMPMeta()}
 * which returns an instance of Adobe's {@link XMPMeta} which exposes the full XMP data set.
 *
 * @author Torsten Skadell
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class XmpDirectory extends Directory
{
    public static final int TAG_XMP_VALUE_COUNT = 0xFFFF;
    // aas
    public static final int TAG_AAS_AFFINEA = "aas:AffineA".hashCode();
    public static final int TAG_AAS_AFFINEC = "aas:AffineC".hashCode();
    public static final int TAG_AAS_AFFINEX = "aas:AffineX".hashCode();
    public static final int TAG_AAS_CROPH = "aas:CropH".hashCode();
    public static final int TAG_AAS_CROPX = "aas:CropX".hashCode();
    public static final int TAG_AAS_CURVE0X = "aas:Curve0x".hashCode();
    public static final int TAG_AAS_CURVE1X = "aas:Curve1x".hashCode();
    public static final int TAG_AAS_CURVE2X = "aas:Curve2x".hashCode();
    public static final int TAG_AAS_CURVE3X = "aas:Curve3x".hashCode();
    public static final int TAG_AAS_CURVE4X = "aas:Curve4x".hashCode();
    public static final int TAG_AAS_FACEBALANCEORIGI = "aas:FaceBalanceOrigI".hashCode();
    public static final int TAG_AAS_FACEBALANCESTRENGTH = "aas:FaceBalanceStrength".hashCode();
    public static final int TAG_AAS_HIGHLIGHTS = "aas:Highlights".hashCode();
    public static final int TAG_AAS_VIBRANCE = "aas:Vibrance".hashCode();

    // acdsee
    public static final int TAG_ACDSEE_AUTHOR = "acdsee:Author".hashCode();
    public static final int TAG_ACDSEE_CATEGORIES = "acdsee:Categories".hashCode();
    public static final int TAG_ACDSEE_DATETIME = "acdsee:DateTime".hashCode();
    public static final int TAG_ACDSEE_EDITSTATUS = "acdsee:EditStatus".hashCode();
    public static final int TAG_ACDSEE_KEYWORDS = "acdsee:Keywords".hashCode();
    public static final int TAG_ACDSEE_OBJECTCYCLE = "acdsee:ObjectCycle".hashCode();
    public static final int TAG_ACDSEE_RATING = "acdsee:Rating".hashCode();
    public static final int TAG_ACDSEE_RELEASEDATE = "acdsee:ReleaseDate".hashCode();
    public static final int TAG_ACDSEE_RPP = "acdsee:RPP".hashCode();
    public static final int TAG_ACDSEE_TAGGED = "acdsee:Tagged".hashCode();

    // Album
    public static final int TAG_ALBUM_NOTES = "album:Notes".hashCode();

    // apple_fi
    public static final int TAG_APPLE_FI_ANGLEINFOROLL = "apple_fi:AngleInfoRoll".hashCode();
    public static final int TAG_APPLE_FI_CONFIDENCELEVEL = "apple_fi:ConfidenceLevel".hashCode();
    public static final int TAG_APPLE_FI_TIMESTAMP = "apple_fi:TimeStamp".hashCode();

    // aux
    public static final int TAG_AUX_APPROXIMATEFOCUSDISTANCE = "aux:ApproximateFocusDistance".hashCode();
    public static final int TAG_AUX_FIRMWARE = "aux:Firmware".hashCode();
    public static final int TAG_AUX_IMAGENUMBER = "aux:ImageNumber".hashCode();
    public static final int TAG_AUX_ISMERGEDPANORAMA = "aux:IsMergedPanorama".hashCode();
    public static final int TAG_AUX_LENS = "aux:Lens".hashCode();
    public static final int TAG_AUX_LENSID = "aux:LensID".hashCode();
    public static final int TAG_AUX_LENSSERIALNUMBER = "aux:LensSerialNumber".hashCode();
    public static final int TAG_AUX_SERIALNUMBER = "aux:SerialNumber".hashCode();

    // cc
    public static final int TAG_CC_ATTRIBUTIONNAME = "cc:AttributionName".hashCode();
    public static final int TAG_CC_DEPRECATEDON = "cc:DeprecatedOn".hashCode();
    public static final int TAG_CC_LEGALCODE = "cc:LegalCode".hashCode();
    public static final int TAG_CC_MOREPERMISSIONS = "cc:MorePermissions".hashCode();
    public static final int TAG_CC_PROHIBITS = "cc:Prohibits".hashCode();
    public static final int TAG_CC_USEGUIDELINES = "cc:UseGuidelines".hashCode();

    // cell
    public static final int TAG_CELL_CELLTOWERID = "cell:CellTowerID".hashCode();
    public static final int TAG_CELL_LOCATIONAREACODE = "cell:LocationAreaCode".hashCode();
    public static final int TAG_CELL_MOBILENETWORKCODE = "cell:MobileNetworkCode".hashCode();

    // creatorAtom
    public static final int TAG_CREATORATOM_AEPROJECTLINK = "creatoratom:AeProjectLink".hashCode();
    public static final int TAG_CREATORATOM_AEPROJECTLINKFULLPATH = "creatoratom:AeProjectLinkFullPath".hashCode();
    public static final int TAG_CREATORATOM_AEPROJECTLINKRENDERQUEUEITEMID = "creatoratom:AeProjectLinkRenderQueueItemID".hashCode();
    public static final int TAG_CREATORATOM_MACATOM = "creatoratom:MacAtom".hashCode();
    public static final int TAG_CREATORATOM_MACATOMINVOCATIONAPPLEEVENT = "creatoratom:MacAtomInvocationAppleEvent".hashCode();
    public static final int TAG_CREATORATOM_WINDOWSATOM = "creatoratom:WindowsAtom".hashCode();
    public static final int TAG_CREATORATOM_WINDOWSATOMINVOCATIONFLAGS = "creatoratom:WindowsAtomInvocationFlags".hashCode();

    // crs
    public static final int TAG_CRS_ALREADYAPPLIED = "crs:AlreadyApplied".hashCode();
    public static final int TAG_CRS_AUTOCONTRAST = "crs:AutoContrast".hashCode();
    public static final int TAG_CRS_AUTOLATERALCA = "crs:AutoLateralCA".hashCode();
    public static final int TAG_CRS_AUTOWHITEVERSION = "crs:AutoWhiteVersion".hashCode();
    public static final int TAG_CRS_BLUEHUE = "crs:BlueHue".hashCode();
    public static final int TAG_CRS_BRIGHTNESS = "crs:Brightness".hashCode();
    public static final int TAG_CRS_CAMERAPROFILEDIGEST = "crs:CameraProfileDigest".hashCode();
    public static final int TAG_CRS_CHROMATICABERRATIONR = "crs:ChromaticAberrationR".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRACTIVE = "crs:CircGradBasedCorrActive".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKS = "crs:CircGradBasedCorrMasks".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKANGLE = "crs:CircGradBasedCorrMaskAngle".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKCENTERVALUE = "crs:CircGradBasedCorrMaskCenterValue".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKDABS = "crs:CircGradBasedCorrMaskDabs".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKFLIPPED = "crs:CircGradBasedCorrMaskFlipped".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKFULLX = "crs:CircGradBasedCorrMaskFullX".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKLEFT = "crs:CircGradBasedCorrMaskLeft".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKMIDPOINT = "crs:CircGradBasedCorrMaskMidpoint".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKRADIUS = "crs:CircGradBasedCorrMaskRadius".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKROUNDNESS = "crs:CircGradBasedCorrMaskRoundness".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKSIZEY = "crs:CircGradBasedCorrMaskSizeY".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKVERSION = "crs:CircGradBasedCorrMaskVersion".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKX = "crs:CircGradBasedCorrMaskX".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMASKZEROX = "crs:CircGradBasedCorrMaskZeroX".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRBRIGHTNESS = "crs:CircGradBasedCorrBrightness".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRCLARITY2012 = "crs:CircGradBasedCorrClarity2012".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRCONTRAST2012 = "crs:CircGradBasedCorrContrast2012".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORREXPOSURE = "crs:CircGradBasedCorrExposure".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRHIGHLIGHTS2012 = "crs:CircGradBasedCorrHighlights2012".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRMOIRE = "crs:CircGradBasedCorrMoire".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRSHADOWS2012 = "crs:CircGradBasedCorrShadows2012".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRTEMPERATURE = "crs:CircGradBasedCorrTemperature".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRHUE = "crs:CircGradBasedCorrHue".hashCode();
    public static final int TAG_CRS_CIRCGRADBASEDCORRWHAT = "crs:CircGradBasedCorrWhat".hashCode();
    public static final int TAG_CRS_CLARITY2012 = "crs:Clarity2012".hashCode();
    public static final int TAG_CRS_COLORNOISEREDUCTIONDETAIL = "crs:ColorNoiseReductionDetail".hashCode();
    public static final int TAG_CRS_CONTRAST = "crs:Contrast".hashCode();
    public static final int TAG_CRS_CONVERTER = "crs:Converter".hashCode();
    public static final int TAG_CRS_CROPANGLE = "crs:CropAngle".hashCode();
    public static final int TAG_CRS_CROPCONSTRAINTOWARP = "crs:CropConstrainToWarp".hashCode();
    public static final int TAG_CRS_CROPLEFT = "crs:CropLeft".hashCode();
    public static final int TAG_CRS_CROPTOP = "crs:CropTop".hashCode();
    public static final int TAG_CRS_CROPUNITS = "crs:CropUnits".hashCode();
    public static final int TAG_CRS_DEFAULTAUTOGRAY = "crs:DefaultAutoGray".hashCode();
    public static final int TAG_CRS_DEFAULTSSPECIFICTOISO = "crs:DefaultsSpecificToISO".hashCode();
    public static final int TAG_CRS_DEFRINGE = "crs:Defringe".hashCode();
    public static final int TAG_CRS_DEFRINGEGREENHUEHI = "crs:DefringeGreenHueHi".hashCode();
    public static final int TAG_CRS_DEFRINGEPURPLEAMOUNT = "crs:DefringePurpleAmount".hashCode();
    public static final int TAG_CRS_DEFRINGEPURPLEHUELO = "crs:DefringePurpleHueLo".hashCode();
    public static final int TAG_CRS_DNGIGNORESIDECARS = "crs:DNGIgnoreSidecars".hashCode();
    public static final int TAG_CRS_EXPOSURE2012 = "crs:Exposure2012".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRECTIONS = "crs:GradientBasedCorrections".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRAMOUNT = "crs:GradientBasedCorrAmount".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKALPHA = "crs:GradientBasedCorrMaskAlpha".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKBOTTOM = "crs:GradientBasedCorrMaskBottom".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKCENTERWEIGHT = "crs:GradientBasedCorrMaskCenterWeight".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKFEATHER = "crs:GradientBasedCorrMaskFeather".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKFLOW = "crs:GradientBasedCorrMaskFlow".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKFULLY = "crs:GradientBasedCorrMaskFullY".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKVALUE = "crs:GradientBasedCorrMaskValue".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKPERIMETERVALUE = "crs:GradientBasedCorrMaskPerimeterValue".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKRIGHT = "crs:GradientBasedCorrMaskRight".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKSIZEX = "crs:GradientBasedCorrMaskSizeX".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKTOP = "crs:GradientBasedCorrMaskTop".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKWHAT = "crs:GradientBasedCorrMaskWhat".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKY = "crs:GradientBasedCorrMaskY".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRMASKZEROY = "crs:GradientBasedCorrMaskZeroY".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRCLARITY = "crs:GradientBasedCorrClarity".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRCONTRAST = "crs:GradientBasedCorrContrast".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRDEFRINGE = "crs:GradientBasedCorrDefringe".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORREXPOSURE2012 = "crs:GradientBasedCorrExposure2012".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRLUMINANCENOISE = "crs:GradientBasedCorrLuminanceNoise".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRSHARPNESS = "crs:GradientBasedCorrSharpness".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRTINT = "crs:GradientBasedCorrTint".hashCode();
    public static final int TAG_CRS_GRADIENTBASEDCORRSATURATION = "crs:GradientBasedCorrSaturation".hashCode();
    public static final int TAG_CRS_GRAINAMOUNT = "crs:GrainAmount".hashCode();
    public static final int TAG_CRS_GRAINSIZE = "crs:GrainSize".hashCode();
    public static final int TAG_CRS_GRAYMIXERBLUE = "crs:GrayMixerBlue".hashCode();
    public static final int TAG_CRS_GRAYMIXERMAGENTA = "crs:GrayMixerMagenta".hashCode();
    public static final int TAG_CRS_GRAYMIXERPURPLE = "crs:GrayMixerPurple".hashCode();
    public static final int TAG_CRS_GRAYMIXERYELLOW = "crs:GrayMixerYellow".hashCode();
    public static final int TAG_CRS_GREENSATURATION = "crs:GreenSaturation".hashCode();
    public static final int TAG_CRS_HASSETTINGS = "crs:HasSettings".hashCode();
    public static final int TAG_CRS_HIGHLIGHTS2012 = "crs:Highlights2012".hashCode();
    public static final int TAG_CRS_HUEADJUSTMENTBLUE = "crs:HueAdjustmentBlue".hashCode();
    public static final int TAG_CRS_HUEADJUSTMENTMAGENTA = "crs:HueAdjustmentMagenta".hashCode();
    public static final int TAG_CRS_HUEADJUSTMENTPURPLE = "crs:HueAdjustmentPurple".hashCode();
    public static final int TAG_CRS_HUEADJUSTMENTYELLOW = "crs:HueAdjustmentYellow".hashCode();
    public static final int TAG_CRS_INCREMENTALTINT = "crs:IncrementalTint".hashCode();
    public static final int TAG_CRS_LENSMANUALDISTORTIONAMOUNT = "crs:LensManualDistortionAmount".hashCode();
    public static final int TAG_CRS_LENSPROFILEDIGEST = "crs:LensProfileDigest".hashCode();
    public static final int TAG_CRS_LENSPROFILEENABLE = "crs:LensProfileEnable".hashCode();
    public static final int TAG_CRS_LENSPROFILEMATCHKEYCAMERAMODELNAME = "crs:LensProfileMatchKeyCameraModelName".hashCode();
    public static final int TAG_CRS_LENSPROFILEMATCHKEYEXIFMODEL = "crs:LensProfileMatchKeyExifModel".hashCode();
    public static final int TAG_CRS_LENSPROFILEMATCHKEYLENSID = "crs:LensProfileMatchKeyLensID".hashCode();
    public static final int TAG_CRS_LENSPROFILEMATCHKEYLENSNAME = "crs:LensProfileMatchKeyLensName".hashCode();
    public static final int TAG_CRS_LENSPROFILENAME = "crs:LensProfileName".hashCode();
    public static final int TAG_CRS_LENSPROFILEVIGNETTINGSCALE = "crs:LensProfileVignettingScale".hashCode();
    public static final int TAG_CRS_LUMINANCEADJUSTMENTBLUE = "crs:LuminanceAdjustmentBlue".hashCode();
    public static final int TAG_CRS_LUMINANCEADJUSTMENTMAGENTA = "crs:LuminanceAdjustmentMagenta".hashCode();
    public static final int TAG_CRS_LUMINANCEADJUSTMENTPURPLE = "crs:LuminanceAdjustmentPurple".hashCode();
    public static final int TAG_CRS_LUMINANCEADJUSTMENTYELLOW = "crs:LuminanceAdjustmentYellow".hashCode();
    public static final int TAG_CRS_LUMINANCENOISEREDUCTIONDETAIL = "crs:LuminanceNoiseReductionDetail".hashCode();
    public static final int TAG_CRS_MOIREFILTER = "crs:MoireFilter".hashCode();
    public static final int TAG_CRS_NEGATIVECACHEMAXIMUMSIZE = "crs:NegativeCacheMaximumSize".hashCode();
    public static final int TAG_CRS_PAINTBASEDCORRECTIONS = "crs:PaintBasedCorrections".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONAMOUNT = "crs:PaintCorrectionAmount".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKALPHA = "crs:PaintCorrectionMaskAlpha".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKBOTTOM = "crs:PaintCorrectionMaskBottom".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKCENTERWEIGHT = "crs:PaintCorrectionMaskCenterWeight".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKFEATHER = "crs:PaintCorrectionMaskFeather".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKFLOW = "crs:PaintCorrectionMaskFlow".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKFULLY = "crs:PaintCorrectionMaskFullY".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKVALUE = "crs:PaintCorrectionMaskValue".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKPERIMETERVALUE = "crs:PaintCorrectionMaskPerimeterValue".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKRIGHT = "crs:PaintCorrectionMaskRight".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKSIZEX = "crs:PaintCorrectionMaskSizeX".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKTOP = "crs:PaintCorrectionMaskTop".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKWHAT = "crs:PaintCorrectionMaskWhat".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKY = "crs:PaintCorrectionMaskY".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONMASKZEROY = "crs:PaintCorrectionMaskZeroY".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONCLARITY = "crs:PaintCorrectionClarity".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONCONTRAST = "crs:PaintCorrectionContrast".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONDEFRINGE = "crs:PaintCorrectionDefringe".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONEXPOSURE2012 = "crs:PaintCorrectionExposure2012".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONLUMINANCENOISE = "crs:PaintCorrectionLuminanceNoise".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONSHARPNESS = "crs:PaintCorrectionSharpness".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONTINT = "crs:PaintCorrectionTint".hashCode();
    public static final int TAG_CRS_PAINTCORRECTIONSATURATION = "crs:PaintCorrectionSaturation".hashCode();
    public static final int TAG_CRS_PARAMETRICDARKS = "crs:ParametricDarks".hashCode();
    public static final int TAG_CRS_PARAMETRICHIGHLIGHTSPLIT = "crs:ParametricHighlightSplit".hashCode();
    public static final int TAG_CRS_PARAMETRICMIDTONESPLIT = "crs:ParametricMidtoneSplit".hashCode();
    public static final int TAG_CRS_PARAMETRICSHADOWSPLIT = "crs:ParametricShadowSplit".hashCode();
    public static final int TAG_CRS_PERSPECTIVEHORIZONTAL = "crs:PerspectiveHorizontal".hashCode();
    public static final int TAG_CRS_PERSPECTIVESCALE = "crs:PerspectiveScale".hashCode();
    public static final int TAG_CRS_PERSPECTIVEVERTICAL = "crs:PerspectiveVertical".hashCode();
    public static final int TAG_CRS_PERSPECTIVEY = "crs:PerspectiveY".hashCode();
    public static final int TAG_CRS_POSTCROPVIGNETTEFEATHER = "crs:PostCropVignetteFeather".hashCode();
    public static final int TAG_CRS_POSTCROPVIGNETTEMIDPOINT = "crs:PostCropVignetteMidpoint".hashCode();
    public static final int TAG_CRS_POSTCROPVIGNETTESTYLE = "crs:PostCropVignetteStyle".hashCode();
    public static final int TAG_CRS_RAWFILENAME = "crs:RawFileName".hashCode();
    public static final int TAG_CRS_REDHUE = "crs:RedHue".hashCode();
    public static final int TAG_CRS_RETOUCHAREAS = "crs:RetouchAreas".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKS = "crs:RetouchAreaMasks".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKANGLE = "crs:RetouchAreaMaskAngle".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKCENTERVALUE = "crs:RetouchAreaMaskCenterValue".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKDABS = "crs:RetouchAreaMaskDabs".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKFLIPPED = "crs:RetouchAreaMaskFlipped".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKFULLX = "crs:RetouchAreaMaskFullX".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKLEFT = "crs:RetouchAreaMaskLeft".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKMIDPOINT = "crs:RetouchAreaMaskMidpoint".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKRADIUS = "crs:RetouchAreaMaskRadius".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKROUNDNESS = "crs:RetouchAreaMaskRoundness".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKSIZEY = "crs:RetouchAreaMaskSizeY".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKVERSION = "crs:RetouchAreaMaskVersion".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKX = "crs:RetouchAreaMaskX".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMASKZEROX = "crs:RetouchAreaMaskZeroX".hashCode();
    public static final int TAG_CRS_RETOUCHAREAMETHOD = "crs:RetouchAreaMethod".hashCode();
    public static final int TAG_CRS_RETOUCHAREAOPACITY = "crs:RetouchAreaOpacity".hashCode();
    public static final int TAG_CRS_RETOUCHAREASOURCESTATE = "crs:RetouchAreaSourceState".hashCode();
    public static final int TAG_CRS_RETOUCHAREASPOTTYPE = "crs:RetouchAreaSpotType".hashCode();
    public static final int TAG_CRS_SATURATION = "crs:Saturation".hashCode();
    public static final int TAG_CRS_SATURATIONADJUSTMENTBLUE = "crs:SaturationAdjustmentBlue".hashCode();
    public static final int TAG_CRS_SATURATIONADJUSTMENTMAGENTA = "crs:SaturationAdjustmentMagenta".hashCode();
    public static final int TAG_CRS_SATURATIONADJUSTMENTPURPLE = "crs:SaturationAdjustmentPurple".hashCode();
    public static final int TAG_CRS_SATURATIONADJUSTMENTYELLOW = "crs:SaturationAdjustmentYellow".hashCode();
    public static final int TAG_CRS_SHADOWS2012 = "crs:Shadows2012".hashCode();
    public static final int TAG_CRS_SHARPENDETAIL = "crs:SharpenDetail".hashCode();
    public static final int TAG_CRS_SHARPENRADIUS = "crs:SharpenRadius".hashCode();
    public static final int TAG_CRS_SMOOTHNESS = "crs:Smoothness".hashCode();
    public static final int TAG_CRS_SPLITTONINGHIGHLIGHTHUE = "crs:SplitToningHighlightHue".hashCode();
    public static final int TAG_CRS_SPLITTONINGSHADOWHUE = "crs:SplitToningShadowHue".hashCode();
    public static final int TAG_CRS_COLORTEMPERATURE = "crs:ColorTemperature".hashCode();
    public static final int TAG_CRS_TINT = "crs:Tint".hashCode();
    public static final int TAG_CRS_TONECURVEBLUE = "crs:ToneCurveBlue".hashCode();
    public static final int TAG_CRS_TONECURVENAME = "crs:ToneCurveName".hashCode();
    public static final int TAG_CRS_TONECURVEPV2012 = "crs:ToneCurvePV2012".hashCode();
    public static final int TAG_CRS_TONECURVEPV2012GREEN = "crs:ToneCurvePV2012Green".hashCode();
    public static final int TAG_CRS_TONECURVERED = "crs:ToneCurveRed".hashCode();
    public static final int TAG_CRS_UPRIGHTCENTERMODE = "crs:UprightCenterMode".hashCode();
    public static final int TAG_CRS_UPRIGHTCENTERNORMY = "crs:UprightCenterNormY".hashCode();
    public static final int TAG_CRS_UPRIGHTFOCALLENGTH35MM = "crs:UprightFocalLength35mm".hashCode();
    public static final int TAG_CRS_UPRIGHTFOURSEGMENTSCOUNT = "crs:UprightFourSegmentsCount".hashCode();
    public static final int TAG_CRS_UPRIGHTTRANSFORM_0 = "crs:UprightTransform_0".hashCode();
    public static final int TAG_CRS_UPRIGHTTRANSFORM_2 = "crs:UprightTransform_2".hashCode();
    public static final int TAG_CRS_UPRIGHTTRANSFORM_4 = "crs:UprightTransform_4".hashCode();
    public static final int TAG_CRS_UPRIGHTTRANSFORMCOUNT = "crs:UprightTransformCount".hashCode();
    public static final int TAG_CRS_VERSION = "crs:Version".hashCode();
    public static final int TAG_CRS_VIGNETTEAMOUNT = "crs:VignetteAmount".hashCode();
    public static final int TAG_CRS_WHAT = "crs:What".hashCode();
    public static final int TAG_CRS_WHITES2012 = "crs:Whites2012".hashCode();

    // dc
    public static final int TAG_DC_CONTRIBUTOR = "dc:Contributor".hashCode();
    public static final int TAG_DC_CREATOR = "dc:Creator".hashCode();
    public static final int TAG_DC_DESCRIPTION = "dc:Description".hashCode();
    public static final int TAG_DC_IDENTIFIER = "dc:Identifier".hashCode();
    public static final int TAG_DC_PUBLISHER = "dc:Publisher".hashCode();
    public static final int TAG_DC_RIGHTS = "dc:Rights".hashCode();
    public static final int TAG_DC_SUBJECT = "dc:Subject".hashCode();
    public static final int TAG_DC_TYPE = "dc:Type".hashCode();

    // dex
    public static final int TAG_DEX_CRC32 = "dex:CRC32".hashCode();
    public static final int TAG_DEX_LICENSETYPE = "dex:LicenseType".hashCode();
    public static final int TAG_DEX_RATING = "dex:Rating".hashCode();
    public static final int TAG_DEX_SHORTDESCRIPTION = "dex:ShortDescription".hashCode();

    // DICOM
    public static final int TAG_DICOM_EQUIPMENTINSTITUTION = "dicom:EquipmentInstitution".hashCode();
    public static final int TAG_DICOM_PATIENTBIRTHDATE = "dicom:PatientBirthDate".hashCode();
    public static final int TAG_DICOM_PATIENTNAME = "dicom:PatientName".hashCode();
    public static final int TAG_DICOM_SERIESDATETIME = "dicom:SeriesDateTime".hashCode();
    public static final int TAG_DICOM_SERIESMODALITY = "dicom:SeriesModality".hashCode();
    public static final int TAG_DICOM_STUDYDATETIME = "dicom:StudyDateTime".hashCode();
    public static final int TAG_DICOM_STUDYID = "dicom:StudyID".hashCode();

    // digiKam
    public static final int TAG_DIGIKAM_CAPTIONSAUTHORNAMES = "digikam:CaptionsAuthorNames".hashCode();
    public static final int TAG_DIGIKAM_COLORLABEL = "digikam:ColorLabel".hashCode();
    public static final int TAG_DIGIKAM_IMAGEUNIQUEID = "digikam:ImageUniqueID".hashCode();
    public static final int TAG_DIGIKAM_PICKLABEL = "digikam:PickLabel".hashCode();

    // exif
    public static final int TAG_EXIF_APERTUREVALUE = "exif:ApertureValue".hashCode();
    public static final int TAG_EXIF_CFAPATTERN = "exif:CFAPattern".hashCode();
    public static final int TAG_EXIF_CFAPATTERNROWS = "exif:CFAPatternRows".hashCode();
    public static final int TAG_EXIF_COLORSPACE = "exif:ColorSpace".hashCode();
    public static final int TAG_EXIF_COMPRESSEDBITSPERPIXEL = "exif:CompressedBitsPerPixel".hashCode();
    public static final int TAG_EXIF_CUSTOMRENDERED = "exif:CustomRendered".hashCode();
    public static final int TAG_EXIF_DATETIMEORIGINAL = "exif:DateTimeOriginal".hashCode();
    public static final int TAG_EXIF_DEVICESETTINGDESCRIPTIONCOLUMNS = "exif:DeviceSettingDescriptionColumns".hashCode();
    public static final int TAG_EXIF_DEVICESETTINGDESCRIPTIONSETTINGS = "exif:DeviceSettingDescriptionSettings".hashCode();
    public static final int TAG_EXIF_EXIFVERSION = "exif:ExifVersion".hashCode();
    public static final int TAG_EXIF_EXPOSUREINDEX = "exif:ExposureIndex".hashCode();
    public static final int TAG_EXIF_EXPOSUREPROGRAM = "exif:ExposureProgram".hashCode();
    public static final int TAG_EXIF_FILESOURCE = "exif:FileSource".hashCode();
    public static final int TAG_EXIF_FLASHENERGY = "exif:FlashEnergy".hashCode();
    public static final int TAG_EXIF_FLASHFUNCTION = "exif:FlashFunction".hashCode();
    public static final int TAG_EXIF_FLASHPIXVERSION = "exif:FlashpixVersion".hashCode();
    public static final int TAG_EXIF_FLASHRETURN = "exif:FlashReturn".hashCode();
    public static final int TAG_EXIF_FOCALLENGTH = "exif:FocalLength".hashCode();
    public static final int TAG_EXIF_FOCALPLANERESOLUTIONUNIT = "exif:FocalPlaneResolutionUnit".hashCode();
    public static final int TAG_EXIF_FOCALPLANEYRESOLUTION = "exif:FocalPlaneYResolution".hashCode();
    public static final int TAG_EXIF_GPSALTITUDE = "exif:GPSAltitude".hashCode();
    public static final int TAG_EXIF_GPSAREAINFORMATION = "exif:GPSAreaInformation".hashCode();
    public static final int TAG_EXIF_GPSDESTBEARINGREF = "exif:GPSDestBearingRef".hashCode();
    public static final int TAG_EXIF_GPSDESTDISTANCEREF = "exif:GPSDestDistanceRef".hashCode();
    public static final int TAG_EXIF_GPSDESTLONGITUDE = "exif:GPSDestLongitude".hashCode();
    public static final int TAG_EXIF_GPSDOP = "exif:GPSDOP".hashCode();
    public static final int TAG_EXIF_GPSIMGDIRECTION = "exif:GPSImgDirection".hashCode();
    public static final int TAG_EXIF_GPSLATITUDE = "exif:GPSLatitude".hashCode();
    public static final int TAG_EXIF_GPSMAPDATUM = "exif:GPSMapDatum".hashCode();
    public static final int TAG_EXIF_GPSPROCESSINGMETHOD = "exif:GPSProcessingMethod".hashCode();
    public static final int TAG_EXIF_GPSSPEED = "exif:GPSSpeed".hashCode();
    public static final int TAG_EXIF_GPSSTATUS = "exif:GPSStatus".hashCode();
    public static final int TAG_EXIF_GPSTRACK = "exif:GPSTrack".hashCode();
    public static final int TAG_EXIF_GPSVERSIONID = "exif:GPSVersionID".hashCode();
    public static final int TAG_EXIF_ISO = "exif:ISO".hashCode();
    public static final int TAG_EXIF_MAKERNOTE = "exif:MakerNote".hashCode();
    public static final int TAG_EXIF_METERINGMODE = "exif:MeteringMode".hashCode();
    public static final int TAG_EXIF_OPTO_ELECTRICCONVFACTOR = "exif:Opto-ElectricConvFactor".hashCode();
    public static final int TAG_EXIF_OECFNAMES = "exif:OECFNames".hashCode();
    public static final int TAG_EXIF_OECFVALUES = "exif:OECFValues".hashCode();
    public static final int TAG_EXIF_EXIFIMAGEHEIGHT = "exif:ExifImageHeight".hashCode();
    public static final int TAG_EXIF_SATURATION = "exif:Saturation".hashCode();
    public static final int TAG_EXIF_SCENETYPE = "exif:SceneType".hashCode();
    public static final int TAG_EXIF_SHARPNESS = "exif:Sharpness".hashCode();
    public static final int TAG_EXIF_SPATIALFREQUENCYRESPONSE = "exif:SpatialFrequencyResponse".hashCode();
    public static final int TAG_EXIF_SPATIALFREQUENCYRESPONSENAMES = "exif:SpatialFrequencyResponseNames".hashCode();
    public static final int TAG_EXIF_SPATIALFREQUENCYRESPONSEVALUES = "exif:SpatialFrequencyResponseValues".hashCode();
    public static final int TAG_EXIF_SUBJECTAREA = "exif:SubjectArea".hashCode();
    public static final int TAG_EXIF_SUBJECTDISTANCERANGE = "exif:SubjectDistanceRange".hashCode();
    public static final int TAG_EXIF_USERCOMMENT = "exif:UserComment".hashCode();

    // exifEX
    public static final int TAG_EXIFEX_SERIALNUMBER = "exifex:SerialNumber".hashCode();
    public static final int TAG_EXIFEX_GAMMA = "exifex:Gamma".hashCode();
    public static final int TAG_EXIFEX_ISOSPEED = "exifex:ISOSpeed".hashCode();
    public static final int TAG_EXIFEX_ISOSPEEDLATITUDEZZZ = "exifex:ISOSpeedLatitudezzz".hashCode();
    public static final int TAG_EXIFEX_LENSMODEL = "exifex:LensModel".hashCode();
    public static final int TAG_EXIFEX_LENSINFO = "exifex:LensInfo".hashCode();
    public static final int TAG_EXIFEX_RECOMMENDEDEXPOSUREINDEX = "exifex:RecommendedExposureIndex".hashCode();
    public static final int TAG_EXIFEX_STANDARDOUTPUTSENSITIVITY = "exifex:StandardOutputSensitivity".hashCode();

    // ExpressionMedia
    public static final int TAG_EXPRESSIONMEDIA_CATALOGSETS = "expressionmedia:CatalogSets".hashCode();
    public static final int TAG_EXPRESSIONMEDIA_PEOPLE = "expressionmedia:People".hashCode();

    // extensis
    public static final int TAG_EXTENSIS_APPROVED = "extensis:Approved".hashCode();
    public static final int TAG_EXTENSIS_CLIENTNAME = "extensis:ClientName".hashCode();
    public static final int TAG_EXTENSIS_JOBSTATUS = "extensis:JobStatus".hashCode();
    public static final int TAG_EXTENSIS_ROUTINGNOTES = "extensis:RoutingNotes".hashCode();

    // fpv
    public static final int TAG_FPV_RICHTEXTCOMMENT = "fpv:RichTextComment".hashCode();

    // GAudio
    public static final int TAG_GAUDIO_AUDIODATA = "gaudio:AudioData".hashCode();

    // GettyImages
    public static final int TAG_GETTYIMAGES_ASSETID = "gettyimages:AssetID".hashCode();
    public static final int TAG_GETTYIMAGES_CAMERAFILENAME = "gettyimages:CameraFilename".hashCode();
    public static final int TAG_GETTYIMAGES_CAMERASERIALNUMBER = "gettyimages:CameraSerialNumber".hashCode();
    public static final int TAG_GETTYIMAGES_EXCLUSIVECOVERAGE = "gettyimages:ExclusiveCoverage".hashCode();
    public static final int TAG_GETTYIMAGES_IMAGERANK = "gettyimages:ImageRank".hashCode();
    public static final int TAG_GETTYIMAGES_ORIGINALCREATEDATETIME = "gettyimages:OriginalCreateDateTime".hashCode();
    public static final int TAG_GETTYIMAGES_PARENTMEDIAEVENTID = "gettyimages:ParentMediaEventID".hashCode();
    public static final int TAG_GETTYIMAGES_PERSONALITY = "gettyimages:Personality".hashCode();
    public static final int TAG_GETTYIMAGES_ROUTINGDESTINATIONS = "gettyimages:RoutingDestinations".hashCode();
    public static final int TAG_GETTYIMAGES_SECONDARYFTP = "gettyimages:SecondaryFTP".hashCode();

    // GImage
    public static final int TAG_GIMAGE_IMAGEDATA = "gimage:ImageData".hashCode();

    // GPano
    public static final int TAG_GPANO_CAPTURESOFTWARE = "gpano:CaptureSoftware".hashCode();
    public static final int TAG_GPANO_CROPPEDAREAIMAGEWIDTHPIXELS = "gpano:CroppedAreaImageWidthPixels".hashCode();
    public static final int TAG_GPANO_CROPPEDAREATOPPIXELS = "gpano:CroppedAreaTopPixels".hashCode();
    public static final int TAG_GPANO_FIRSTPHOTODATE = "gpano:FirstPhotoDate".hashCode();
    public static final int TAG_GPANO_FULLPANOWIDTHPIXELS = "gpano:FullPanoWidthPixels".hashCode();
    public static final int TAG_GPANO_INITIALHORIZONTALFOVDEGREES = "gpano:InitialHorizontalFOVDegrees".hashCode();
    public static final int TAG_GPANO_INITIALVIEWPITCHDEGREES = "gpano:InitialViewPitchDegrees".hashCode();
    public static final int TAG_GPANO_LARGESTVALIDINTERIORRECTHEIGHT = "gpano:LargestValidInteriorRectHeight".hashCode();
    public static final int TAG_GPANO_LARGESTVALIDINTERIORRECTTOP = "gpano:LargestValidInteriorRectTop".hashCode();
    public static final int TAG_GPANO_LASTPHOTODATE = "gpano:LastPhotoDate".hashCode();
    public static final int TAG_GPANO_POSEPITCHDEGREES = "gpano:PosePitchDegrees".hashCode();
    public static final int TAG_GPANO_PROJECTIONTYPE = "gpano:ProjectionType".hashCode();
    public static final int TAG_GPANO_STITCHINGSOFTWARE = "gpano:StitchingSoftware".hashCode();

    // GSpherical
    public static final int TAG_GSPHERICAL_CROPPEDAREAIMAGEHEIGHTPIXELS = "gspherical:CroppedAreaImageHeightPixels".hashCode();
    public static final int TAG_GSPHERICAL_CROPPEDAREALEFTPIXELS = "gspherical:CroppedAreaLeftPixels".hashCode();
    public static final int TAG_GSPHERICAL_FULLPANOHEIGHTPIXELS = "gspherical:FullPanoHeightPixels".hashCode();
    public static final int TAG_GSPHERICAL_INITIALVIEWHEADINGDEGREES = "gspherical:InitialViewHeadingDegrees".hashCode();
    public static final int TAG_GSPHERICAL_INITIALVIEWROLLDEGREES = "gspherical:InitialViewRollDegrees".hashCode();
    public static final int TAG_GSPHERICAL_SOURCECOUNT = "gspherical:SourceCount".hashCode();
    public static final int TAG_GSPHERICAL_STEREOMODE = "gspherical:StereoMode".hashCode();
    public static final int TAG_GSPHERICAL_STITCHINGSOFTWARE = "gspherical:StitchingSoftware".hashCode();

    // ics
    public static final int TAG_ICS_APPVERSION = "ics:AppVersion".hashCode();
    public static final int TAG_ICS_SUBVERSIONS = "ics:SubVersions".hashCode();
    public static final int TAG_ICS_SUBVERSIONREFERENCE = "ics:SubVersionReference".hashCode();
    public static final int TAG_ICS_LABELNAME1 = "ics:LabelName1".hashCode();
    public static final int TAG_ICS_REFERENCE1 = "ics:Reference1".hashCode();
    public static final int TAG_ICS_LABELNAME2 = "ics:LabelName2".hashCode();
    public static final int TAG_ICS_REFERENCE2 = "ics:Reference2".hashCode();
    public static final int TAG_ICS_LABELNAME3 = "ics:LabelName3".hashCode();
    public static final int TAG_ICS_REFERENCE3 = "ics:Reference3".hashCode();
    public static final int TAG_ICS_LABELNAME4 = "ics:LabelName4".hashCode();
    public static final int TAG_ICS_REFERENCE4 = "ics:Reference4".hashCode();
    public static final int TAG_ICS_LABELNAME5 = "ics:LabelName5".hashCode();
    public static final int TAG_ICS_REFERENCE5 = "ics:Reference5".hashCode();
    public static final int TAG_ICS_LABELNAME6 = "ics:LabelName6".hashCode();
    public static final int TAG_ICS_REFERENCE6 = "ics:Reference6".hashCode();

    // iptcCore
    public static final int TAG_IPTCCORE_COUNTRYCODE = "iptccore:CountryCode".hashCode();
    public static final int TAG_IPTCCORE_CREATORCITY = "iptccore:CreatorCity".hashCode();
    public static final int TAG_IPTCCORE_CREATORADDRESS = "iptccore:CreatorAddress".hashCode();
    public static final int TAG_IPTCCORE_CREATORREGION = "iptccore:CreatorRegion".hashCode();
    public static final int TAG_IPTCCORE_CREATORWORKTELEPHONE = "iptccore:CreatorWorkTelephone".hashCode();
    public static final int TAG_IPTCCORE_INTELLECTUALGENRE = "iptccore:IntellectualGenre".hashCode();
    public static final int TAG_IPTCCORE_SCENE = "iptccore:Scene".hashCode();

    // iptcExt
    public static final int TAG_IPTCEXT_ABOUTCVTERM = "iptcext:AboutCvTerm".hashCode();
    public static final int TAG_IPTCEXT_ABOUTCVTERMID = "iptcext:AboutCvTermId".hashCode();
    public static final int TAG_IPTCEXT_ABOUTCVTERMREFINEDABOUT = "iptcext:AboutCvTermRefinedAbout".hashCode();
    public static final int TAG_IPTCEXT_ARTWORKOROBJECT = "iptcext:ArtworkOrObject".hashCode();
    public static final int TAG_IPTCEXT_ARTWORKCONTENTDESCRIPTION = "iptcext:ArtworkContentDescription".hashCode();
    public static final int TAG_IPTCEXT_ARTWORKCOPYRIGHTNOTICE = "iptcext:ArtworkCopyrightNotice".hashCode();
    public static final int TAG_IPTCEXT_ARTWORKCREATORID = "iptcext:ArtworkCreatorID".hashCode();
    public static final int TAG_IPTCEXT_ARTWORKCOPYRIGHTOWNERNAME = "iptcext:ArtworkCopyrightOwnerName".hashCode();
    public static final int TAG_IPTCEXT_ARTWORKLICENSORNAME = "iptcext:ArtworkLicensorName".hashCode();
    public static final int TAG_IPTCEXT_ARTWORKPHYSICALDESCRIPTION = "iptcext:ArtworkPhysicalDescription".hashCode();
    public static final int TAG_IPTCEXT_ARTWORKSOURCEINVENTORYNO = "iptcext:ArtworkSourceInventoryNo".hashCode();
    public static final int TAG_IPTCEXT_ARTWORKSTYLEPERIOD = "iptcext:ArtworkStylePeriod".hashCode();
    public static final int TAG_IPTCEXT_AUDIOBITRATE = "iptcext:AudioBitrate".hashCode();
    public static final int TAG_IPTCEXT_AUDIOCHANNELCOUNT = "iptcext:AudioChannelCount".hashCode();
    public static final int TAG_IPTCEXT_CONTAINERFORMAT = "iptcext:ContainerFormat".hashCode();
    public static final int TAG_IPTCEXT_CONTAINERFORMATNAME = "iptcext:ContainerFormatName".hashCode();
    public static final int TAG_IPTCEXT_CONTRIBUTORIDENTIFIER = "iptcext:ContributorIdentifier".hashCode();
    public static final int TAG_IPTCEXT_CONTRIBUTORROLE = "iptcext:ContributorRole".hashCode();
    public static final int TAG_IPTCEXT_CREATOR = "iptcext:Creator".hashCode();
    public static final int TAG_IPTCEXT_CREATORNAME = "iptcext:CreatorName".hashCode();
    public static final int TAG_IPTCEXT_CONTROLLEDVOCABULARYTERM = "iptcext:ControlledVocabularyTerm".hashCode();
    public static final int TAG_IPTCEXT_DATAONSCREENREGION = "iptcext:DataOnScreenRegion".hashCode();
    public static final int TAG_IPTCEXT_DATAONSCREENREGIONH = "iptcext:DataOnScreenRegionH".hashCode();
    public static final int TAG_IPTCEXT_DATAONSCREENREGIONUNIT = "iptcext:DataOnScreenRegionUnit".hashCode();
    public static final int TAG_IPTCEXT_DATAONSCREENREGIONX = "iptcext:DataOnScreenRegionX".hashCode();
    public static final int TAG_IPTCEXT_DIGITALIMAGEGUID = "iptcext:DigitalImageGUID".hashCode();
    public static final int TAG_IPTCEXT_DIGITALSOURCETYPE = "iptcext:DigitalSourceType".hashCode();
    public static final int TAG_IPTCEXT_DOPESHEETLINK = "iptcext:DopesheetLink".hashCode();
    public static final int TAG_IPTCEXT_DOPESHEETLINKLINKQUALIFIER = "iptcext:DopesheetLinkLinkQualifier".hashCode();
    public static final int TAG_IPTCEXT_EMBEDDEDENCODEDRIGHTSEXPR = "iptcext:EmbeddedEncodedRightsExpr".hashCode();
    public static final int TAG_IPTCEXT_EMBEDDEDENCODEDRIGHTSEXPRLANGID = "iptcext:EmbeddedEncodedRightsExprLangID".hashCode();
    public static final int TAG_IPTCEXT_EPISODEIDENTIFIER = "iptcext:EpisodeIdentifier".hashCode();
    public static final int TAG_IPTCEXT_EPISODENUMBER = "iptcext:EpisodeNumber".hashCode();
    public static final int TAG_IPTCEXT_SHOWNEVENT = "iptcext:ShownEvent".hashCode();
    public static final int TAG_IPTCEXT_SHOWNEVENTNAME = "iptcext:ShownEventName".hashCode();
    public static final int TAG_IPTCEXT_FEEDIDENTIFIER = "iptcext:FeedIdentifier".hashCode();
    public static final int TAG_IPTCEXT_GENRECVID = "iptcext:GenreCvId".hashCode();
    public static final int TAG_IPTCEXT_GENRECVTERMNAME = "iptcext:GenreCvTermName".hashCode();
    public static final int TAG_IPTCEXT_HEADLINE = "iptcext:Headline".hashCode();
    public static final int TAG_IPTCEXT_LINKEDENCRIGHTSEXPR = "iptcext:LinkedEncRightsExpr".hashCode();
    public static final int TAG_IPTCEXT_LINKEDENCODEDRIGHTSEXPRTYPE = "iptcext:LinkedEncodedRightsExprType".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONCREATED = "iptcext:LocationCreated".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONCREATEDCOUNTRYCODE = "iptcext:LocationCreatedCountryCode".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONCREATEDGPSALTITUDE = "iptcext:LocationCreatedGPSAltitude".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONCREATEDGPSLONGITUDE = "iptcext:LocationCreatedGPSLongitude".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONCREATEDLOCATIONID = "iptcext:LocationCreatedLocationId".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONCREATEDPROVINCESTATE = "iptcext:LocationCreatedProvinceState".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONCREATEDWORLDREGION = "iptcext:LocationCreatedWorldRegion".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONSHOWNCITY = "iptcext:LocationShownCity".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONSHOWNCOUNTRYNAME = "iptcext:LocationShownCountryName".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONSHOWNGPSLATITUDE = "iptcext:LocationShownGPSLatitude".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONSHOWNIDENTIFIER = "iptcext:LocationShownIdentifier".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONSHOWNLOCATIONNAME = "iptcext:LocationShownLocationName".hashCode();
    public static final int TAG_IPTCEXT_LOCATIONSHOWNSUBLOCATION = "iptcext:LocationShownSublocation".hashCode();
    public static final int TAG_IPTCEXT_MAXAVAILHEIGHT = "iptcext:MaxAvailHeight".hashCode();
    public static final int TAG_IPTCEXT_MODELAGE = "iptcext:ModelAge".hashCode();
    public static final int TAG_IPTCEXT_ORGANISATIONINIMAGENAME = "iptcext:OrganisationInImageName".hashCode();
    public static final int TAG_IPTCEXT_PERSONHEARDIDENTIFIER = "iptcext:PersonHeardIdentifier".hashCode();
    public static final int TAG_IPTCEXT_PERSONINIMAGE = "iptcext:PersonInImage".hashCode();
    public static final int TAG_IPTCEXT_PERSONINIMAGECHARACTERISTIC = "iptcext:PersonInImageCharacteristic".hashCode();
    public static final int TAG_IPTCEXT_PERSONINIMAGECVTERMID = "iptcext:PersonInImageCvTermId".hashCode();
    public static final int TAG_IPTCEXT_PERSONINIMAGECVTERMREFINEDABOUT = "iptcext:PersonInImageCvTermRefinedAbout".hashCode();
    public static final int TAG_IPTCEXT_PERSONINIMAGEID = "iptcext:PersonInImageId".hashCode();
    public static final int TAG_IPTCEXT_PRODUCTINIMAGE = "iptcext:ProductInImage".hashCode();
    public static final int TAG_IPTCEXT_PRODUCTINIMAGEGTIN = "iptcext:ProductInImageGTIN".hashCode();
    public static final int TAG_IPTCEXT_PUBLICATIONEVENT = "iptcext:PublicationEvent".hashCode();
    public static final int TAG_IPTCEXT_PUBLICATIONEVENTIDENTIFIER = "iptcext:PublicationEventIdentifier".hashCode();
    public static final int TAG_IPTCEXT_RATING = "iptcext:Rating".hashCode();
    public static final int TAG_IPTCEXT_RATINGREGIONCITY = "iptcext:RatingRegionCity".hashCode();
    public static final int TAG_IPTCEXT_RATINGREGIONCOUNTRYNAME = "iptcext:RatingRegionCountryName".hashCode();
    public static final int TAG_IPTCEXT_RATINGREGIONGPSLATITUDE = "iptcext:RatingRegionGPSLatitude".hashCode();
    public static final int TAG_IPTCEXT_RATINGREGIONIDENTIFIER = "iptcext:RatingRegionIdentifier".hashCode();
    public static final int TAG_IPTCEXT_RATINGREGIONLOCATIONNAME = "iptcext:RatingRegionLocationName".hashCode();
    public static final int TAG_IPTCEXT_RATINGREGIONSUBLOCATION = "iptcext:RatingRegionSublocation".hashCode();
    public static final int TAG_IPTCEXT_RATINGSCALEMAXVALUE = "iptcext:RatingScaleMaxValue".hashCode();
    public static final int TAG_IPTCEXT_RATINGSOURCELINK = "iptcext:RatingSourceLink".hashCode();
    public static final int TAG_IPTCEXT_RATINGVALUELOGOLINK = "iptcext:RatingValueLogoLink".hashCode();
    public static final int TAG_IPTCEXT_REGISTRYENTRYROLE = "iptcext:RegistryEntryRole".hashCode();
    public static final int TAG_IPTCEXT_REGISTRYORGANISATIONID = "iptcext:RegistryOrganisationID".hashCode();
    public static final int TAG_IPTCEXT_SEASON = "iptcext:Season".hashCode();
    public static final int TAG_IPTCEXT_SEASONNAME = "iptcext:SeasonName".hashCode();
    public static final int TAG_IPTCEXT_SERIES = "iptcext:Series".hashCode();
    public static final int TAG_IPTCEXT_SERIESNAME = "iptcext:SeriesName".hashCode();
    public static final int TAG_IPTCEXT_STREAMREADY = "iptcext:StreamReady".hashCode();
    public static final int TAG_IPTCEXT_SUPPLYCHAINSOURCE = "iptcext:SupplyChainSource".hashCode();
    public static final int TAG_IPTCEXT_SUPPLYCHAINSOURCENAME = "iptcext:SupplyChainSourceName".hashCode();
    public static final int TAG_IPTCEXT_TEMPORALCOVERAGEFROM = "iptcext:TemporalCoverageFrom".hashCode();
    public static final int TAG_IPTCEXT_TRANSCRIPT = "iptcext:Transcript".hashCode();
    public static final int TAG_IPTCEXT_TRANSCRIPTLINKLINK = "iptcext:TranscriptLinkLink".hashCode();
    public static final int TAG_IPTCEXT_VIDEOBITRATE = "iptcext:VideoBitrate".hashCode();
    public static final int TAG_IPTCEXT_VIDEODISPLAYASPECTRATIO = "iptcext:VideoDisplayAspectRatio".hashCode();
    public static final int TAG_IPTCEXT_VIDEOSHOTTYPE = "iptcext:VideoShotType".hashCode();
    public static final int TAG_IPTCEXT_VIDEOSHOTTYPENAME = "iptcext:VideoShotTypeName".hashCode();
    public static final int TAG_IPTCEXT_VISUALCOLOR = "iptcext:VisualColor".hashCode();
    public static final int TAG_IPTCEXT_WORKFLOWTAGCVID = "iptcext:WorkflowTagCvId".hashCode();
    public static final int TAG_IPTCEXT_WORKFLOWTAGCVTERMNAME = "iptcext:WorkflowTagCvTermName".hashCode();

    // Lightroom
    public static final int TAG_LIGHTROOM_HIERARCHICALSUBJECT = "lightroom:HierarchicalSubject".hashCode();

    // MediaPro
    public static final int TAG_MEDIAPRO_CATALOGSETS = "mediapro:CatalogSets".hashCode();
    public static final int TAG_MEDIAPRO_LOCATION = "mediapro:Location".hashCode();
    public static final int TAG_MEDIAPRO_STATUS = "mediapro:Status".hashCode();

    // pdf
    public static final int TAG_PDF_AUTHOR = "pdf:Author".hashCode();
    public static final int TAG_PDF_CREATIONDATE = "pdf:CreationDate".hashCode();
    public static final int TAG_PDF_KEYWORDS = "pdf:Keywords".hashCode();
    public static final int TAG_PDF_MODDATE = "pdf:ModDate".hashCode();
    public static final int TAG_PDF_PRODUCER = "pdf:Producer".hashCode();
    public static final int TAG_PDF_TITLE = "pdf:Title".hashCode();

// pdfx

    // photoshop
    public static final int TAG_PHOTOSHOP_AUTHORSPOSITION = "photoshop:AuthorsPosition".hashCode();
    public static final int TAG_PHOTOSHOP_CATEGORY = "photoshop:Category".hashCode();
    public static final int TAG_PHOTOSHOP_COLORMODE = "photoshop:ColorMode".hashCode();
    public static final int TAG_PHOTOSHOP_CREDIT = "photoshop:Credit".hashCode();
    public static final int TAG_PHOTOSHOP_DOCUMENTANCESTORS = "photoshop:DocumentAncestors".hashCode();
    public static final int TAG_PHOTOSHOP_HEADLINE = "photoshop:Headline".hashCode();
    public static final int TAG_PHOTOSHOP_ICCPROFILENAME = "photoshop:ICCProfileName".hashCode();
    public static final int TAG_PHOTOSHOP_LEGACYIPTCDIGEST = "photoshop:LegacyIPTCDigest".hashCode();
    public static final int TAG_PHOTOSHOP_SOURCE = "photoshop:Source".hashCode();
    public static final int TAG_PHOTOSHOP_SUPPLEMENTALCATEGORIES = "photoshop:SupplementalCategories".hashCode();
    public static final int TAG_PHOTOSHOP_TEXTLAYERNAME = "photoshop:TextLayerName".hashCode();
    public static final int TAG_PHOTOSHOP_TRANSMISSIONREFERENCE = "photoshop:TransmissionReference".hashCode();

    // PixelLive
    public static final int TAG_PIXELLIVE_AUTHOR = "pixellive:Author".hashCode();
    public static final int TAG_PIXELLIVE_COPYRIGHT = "pixellive:Copyright".hashCode();
    public static final int TAG_PIXELLIVE_GENRE = "pixellive:Genre".hashCode();

    // pmi
    public static final int TAG_PMI_COLOR = "pmi:Color".hashCode();
    public static final int TAG_PMI_DISPLAYNAME = "pmi:DisplayName".hashCode();
    public static final int TAG_PMI_EVENTALIAS = "pmi:EventAlias".hashCode();
    public static final int TAG_PMI_EVENTSTART = "pmi:EventStart".hashCode();
    public static final int TAG_PMI_EVENTTYPE = "pmi:EventType".hashCode();
    public static final int TAG_PMI_FRAMING = "pmi:Framing".hashCode();
    public static final int TAG_PMI_MAKE = "pmi:Make".hashCode();
    public static final int TAG_PMI_MODEL = "pmi:Model".hashCode();
    public static final int TAG_PMI_OBJECTDESCRIPTION = "pmi:ObjectDescription".hashCode();
    public static final int TAG_PMI_OBJECTTYPE = "pmi:ObjectType".hashCode();
    public static final int TAG_PMI_POSITIONDESCRIPTOR = "pmi:PositionDescriptor".hashCode();
    public static final int TAG_PMI_PRODUCTIDTYPE = "pmi:ProductIDType".hashCode();
    public static final int TAG_PMI_SEQUENCENAME = "pmi:SequenceName".hashCode();
    public static final int TAG_PMI_SEQUENCETOTALNUMBER = "pmi:SequenceTotalNumber".hashCode();
    public static final int TAG_PMI_SHOOTID = "pmi:ShootID".hashCode();
    public static final int TAG_PMI_SLIDESHOWNUMBER = "pmi:SlideshowNumber".hashCode();
    public static final int TAG_PMI_VIEWPOINT = "pmi:Viewpoint".hashCode();

    // prism
    public static final int TAG_PRISM_ACEDEMICFIELD = "prism:AcedemicField".hashCode();
    public static final int TAG_PRISM_AGGREGATIONTYPE = "prism:AggregationType".hashCode();
    public static final int TAG_PRISM_ALTERNATETITLEA_LANG = "prism:AlternateTitleA-lang".hashCode();
    public static final int TAG_PRISM_ALTERNATETITLETEXT = "prism:AlternateTitleText".hashCode();
    public static final int TAG_PRISM_BLOGURL = "prism:BlogURL".hashCode();
    public static final int TAG_PRISM_BYTECOUNT = "prism:ByteCount".hashCode();
    public static final int TAG_PRISM_CHANNELA_LANG = "prism:ChannelA-lang".hashCode();
    public static final int TAG_PRISM_CHANNELSUBCHANNEL1 = "prism:ChannelSubchannel1".hashCode();
    public static final int TAG_PRISM_CHANNELSUBCHANNEL3 = "prism:ChannelSubchannel3".hashCode();
    public static final int TAG_PRISM_COMPLIANCEPROFILE = "prism:ComplianceProfile".hashCode();
    public static final int TAG_PRISM_COPYRIGHTYEAR = "prism:CopyrightYear".hashCode();
    public static final int TAG_PRISM_COVERDATE = "prism:CoverDate".hashCode();
    public static final int TAG_PRISM_CREATIONDATE = "prism:CreationDate".hashCode();
    public static final int TAG_PRISM_DEVICE = "prism:Device".hashCode();
    public static final int TAG_PRISM_DOI = "prism:DOI".hashCode();
    public static final int TAG_PRISM_EISSN = "prism:EIssn".hashCode();
    public static final int TAG_PRISM_EVENT = "prism:Event".hashCode();
    public static final int TAG_PRISM_HASALTERNATIVE = "prism:HasAlternative".hashCode();
    public static final int TAG_PRISM_HASCORRECTIONA_LANG = "prism:HasCorrectionA-lang".hashCode();
    public static final int TAG_PRISM_HASCORRECTIONTEXT = "prism:HasCorrectionText".hashCode();
    public static final int TAG_PRISM_INDUSTRY = "prism:Industry".hashCode();
    public static final int TAG_PRISM_ISBN = "prism:ISBN".hashCode();
    public static final int TAG_PRISM_ISSN = "prism:ISSN".hashCode();
    public static final int TAG_PRISM_ISSUENAME = "prism:IssueName".hashCode();
    public static final int TAG_PRISM_ISSUETYPE = "prism:IssueType".hashCode();
    public static final int TAG_PRISM_KEYWORD = "prism:Keyword".hashCode();
    public static final int TAG_PRISM_KILLDATEA_PLATFORM = "prism:KillDateA-platform".hashCode();
    public static final int TAG_PRISM_LINK = "prism:Link".hashCode();
    public static final int TAG_PRISM_MODIFICATIONDATE = "prism:ModificationDate".hashCode();
    public static final int TAG_PRISM_NUMBER = "prism:Number".hashCode();
    public static final int TAG_PRISM_OFFSALEDATE = "prism:OffSaleDate".hashCode();
    public static final int TAG_PRISM_OFFSALEDATEDATE = "prism:OffSaleDateDate".hashCode();
    public static final int TAG_PRISM_ONSALEDATEA_PLATFORM = "prism:OnSaleDateA-platform".hashCode();
    public static final int TAG_PRISM_ONSALEDAY = "prism:OnSaleDay".hashCode();
    public static final int TAG_PRISM_ONSALEDAYDAY = "prism:OnSaleDayDay".hashCode();
    public static final int TAG_PRISM_ORIGINPLATFORM = "prism:OriginPlatform".hashCode();
    public static final int TAG_PRISM_PAGEPROGRESSIONDIRECTION = "prism:PageProgressionDirection".hashCode();
    public static final int TAG_PRISM_PERSON = "prism:Person".hashCode();
    public static final int TAG_PRISM_PRODUCTCODE = "prism:ProductCode".hashCode();
    public static final int TAG_PRISM_PUBLICATIONDATE = "prism:PublicationDate".hashCode();
    public static final int TAG_PRISM_PUBLICATIONDATEDATE = "prism:PublicationDateDate".hashCode();
    public static final int TAG_PRISM_PUBLICATIONDISPLAYDATEA_PLATFORM = "prism:PublicationDisplayDateA-platform".hashCode();
    public static final int TAG_PRISM_PUBLICATIONNAME = "prism:PublicationName".hashCode();
    public static final int TAG_PRISM_RATING = "prism:Rating".hashCode();
    public static final int TAG_PRISM_SECTION = "prism:Section".hashCode();
    public static final int TAG_PRISM_SERIESNUMBER = "prism:SeriesNumber".hashCode();
    public static final int TAG_PRISM_SPORT = "prism:Sport".hashCode();
    public static final int TAG_PRISM_SUBSECTION1 = "prism:Subsection1".hashCode();
    public static final int TAG_PRISM_SUBSECTION3 = "prism:Subsection3".hashCode();
    public static final int TAG_PRISM_SUBTITLE = "prism:Subtitle".hashCode();
    public static final int TAG_PRISM_SUPPLEMENTSTARTINGPAGE = "prism:SupplementStartingPage".hashCode();
    public static final int TAG_PRISM_TEASER = "prism:Teaser".hashCode();
    public static final int TAG_PRISM_TIMEPERIOD = "prism:TimePeriod".hashCode();
    public static final int TAG_PRISM_URLA_PLATFORM = "prism:URLA-platform".hashCode();
    public static final int TAG_PRISM_USPSNUMBER = "prism:UspsNumber".hashCode();
    public static final int TAG_PRISM_VOLUME = "prism:Volume".hashCode();

    // prl
    public static final int TAG_PRL_GEOGRAPHY = "prl:Geography".hashCode();
    public static final int TAG_PRL_USAGE = "prl:Usage".hashCode();

    // prm
    public static final int TAG_PRM_COOKINGEQUIPMENT = "prm:CookingEquipment".hashCode();
    public static final int TAG_PRM_COURSE = "prm:Course".hashCode();
    public static final int TAG_PRM_DIETARYNEEDS = "prm:DietaryNeeds".hashCode();
    public static final int TAG_PRM_DURATION = "prm:Duration".hashCode();
    public static final int TAG_PRM_MAININGREDIENT = "prm:MainIngredient".hashCode();
    public static final int TAG_PRM_RECIPEENDINGPAGE = "prm:RecipeEndingPage".hashCode();
    public static final int TAG_PRM_RECIPESOURCE = "prm:RecipeSource".hashCode();
    public static final int TAG_PRM_RECIPETITLE = "prm:RecipeTitle".hashCode();
    public static final int TAG_PRM_SKILLLEVEL = "prm:SkillLevel".hashCode();
    public static final int TAG_PRM_YIELD = "prm:Yield".hashCode();

    // pur
    public static final int TAG_PUR_ADULTCONTENTWARNING = "pur:AdultContentWarning".hashCode();
    public static final int TAG_PUR_COPYRIGHT = "pur:Copyright".hashCode();
    public static final int TAG_PUR_EMBARGODATE = "pur:EmbargoDate".hashCode();
    public static final int TAG_PUR_EXPIRATIONDATE = "pur:ExpirationDate".hashCode();
    public static final int TAG_PUR_OPTIONENDDATE = "pur:OptionEndDate".hashCode();
    public static final int TAG_PUR_RESTRICTIONS = "pur:Restrictions".hashCode();
    public static final int TAG_PUR_RIGHTSAGENT = "pur:RightsAgent".hashCode();

    // rdf
    public static final int TAG_RDF_ABOUT = "rdf:About".hashCode();

    // swf
    public static final int TAG_SWF_BACKGROUNDALPHA = "swf:BackgroundAlpha".hashCode();
    public static final int TAG_SWF_MAXSTORAGE = "swf:MaxStorage".hashCode();

    // tiff
    public static final int TAG_TIFF_ARTIST = "tiff:Artist".hashCode();
    public static final int TAG_TIFF_COMPRESSION = "tiff:Compression".hashCode();
    public static final int TAG_TIFF_DATETIME = "tiff:DateTime".hashCode();
    public static final int TAG_TIFF_IMAGEHEIGHT = "tiff:ImageHeight".hashCode();
    public static final int TAG_TIFF_MAKE = "tiff:Make".hashCode();
    public static final int TAG_TIFF_NATIVEDIGEST = "tiff:NativeDigest".hashCode();
    public static final int TAG_TIFF_PHOTOMETRICINTERPRETATION = "tiff:PhotometricInterpretation".hashCode();
    public static final int TAG_TIFF_PRIMARYCHROMATICITIES = "tiff:PrimaryChromaticities".hashCode();
    public static final int TAG_TIFF_RESOLUTIONUNIT = "tiff:ResolutionUnit".hashCode();
    public static final int TAG_TIFF_SOFTWARE = "tiff:Software".hashCode();
    public static final int TAG_TIFF_WHITEPOINT = "tiff:WhitePoint".hashCode();
    public static final int TAG_TIFF_YCBCRCOEFFICIENTS = "tiff:YCbCrCoefficients".hashCode();
    public static final int TAG_TIFF_YCBCRSUBSAMPLING = "tiff:YCbCrSubSampling".hashCode();

    // x
    public static final int TAG_X_XMPTOOLKIT = "x:XMPToolkit".hashCode();

    // xmp
    public static final int TAG_XMP_ADVISORY = "xmp:Advisory".hashCode();
    public static final int TAG_XMP_BASEURL = "xmp:BaseURL".hashCode();
    public static final int TAG_XMP_CREATORTOOL = "xmp:CreatorTool".hashCode();
    public static final int TAG_XMP_FORMAT = "xmp:Format".hashCode();
    public static final int TAG_XMP_KEYWORDS = "xmp:Keywords".hashCode();
    public static final int TAG_XMP_METADATADATE = "xmp:MetadataDate".hashCode();
    public static final int TAG_XMP_NICKNAME = "xmp:Nickname".hashCode();
    public static final int TAG_XMP_PAGEIMAGEFORMAT = "xmp:PageImageFormat".hashCode();
    public static final int TAG_XMP_PAGEIMAGE = "xmp:PageImage".hashCode();
    public static final int TAG_XMP_PAGEIMAGEWIDTH = "xmp:PageImageWidth".hashCode();
    public static final int TAG_XMP_THUMBNAILS = "xmp:Thumbnails".hashCode();
    public static final int TAG_XMP_THUMBNAILHEIGHT = "xmp:ThumbnailHeight".hashCode();
    public static final int TAG_XMP_THUMBNAILWIDTH = "xmp:ThumbnailWidth".hashCode();

    // xmpBJ
    public static final int TAG_XMPBJ_JOBREF = "xmpbj:JobRef".hashCode();
    public static final int TAG_XMPBJ_JOBREFNAME = "xmpbj:JobRefName".hashCode();

    // xmpDM
    public static final int TAG_XMPDM_ABSPEAKAUDIOFILEPATH = "xmpdm:AbsPeakAudioFilePath".hashCode();
    public static final int TAG_XMPDM_ALTTAPENAME = "xmpdm:AltTapeName".hashCode();
    public static final int TAG_XMPDM_ALTTIMECODETIMEFORMAT = "xmpdm:AltTimecodeTimeFormat".hashCode();
    public static final int TAG_XMPDM_ALTTIMECODEVALUE = "xmpdm:AltTimecodeValue".hashCode();
    public static final int TAG_XMPDM_AUDIOCHANNELTYPE = "xmpdm:AudioChannelType".hashCode();
    public static final int TAG_XMPDM_AUDIOMODDATE = "xmpdm:AudioModDate".hashCode();
    public static final int TAG_XMPDM_AUDIOSAMPLETYPE = "xmpdm:AudioSampleType".hashCode();
    public static final int TAG_XMPDM_BEATSPLICEPARAMSRISEINDECIBEL = "xmpdm:BeatSpliceParamsRiseInDecibel".hashCode();
    public static final int TAG_XMPDM_BEATSPLICEPARAMSRISEINTIMEDURATIONSCALE = "xmpdm:BeatSpliceParamsRiseInTimeDurationScale".hashCode();
    public static final int TAG_XMPDM_BEATSPLICEPARAMSUSEFILEBEATSMARKER = "xmpdm:BeatSpliceParamsUseFileBeatsMarker".hashCode();
    public static final int TAG_XMPDM_CAMERALABEL = "xmpdm:CameraLabel".hashCode();
    public static final int TAG_XMPDM_CAMERAMOVE = "xmpdm:CameraMove".hashCode();
    public static final int TAG_XMPDM_DMCOMMENT = "xmpdm:DMComment".hashCode();
    public static final int TAG_XMPDM_CONTRIBUTEDMEDIA = "xmpdm:ContributedMedia".hashCode();
    public static final int TAG_XMPDM_CONTRIBUTEDMEDIADURATIONSCALE = "xmpdm:ContributedMediaDurationScale".hashCode();
    public static final int TAG_XMPDM_CONTRIBUTEDMEDIAMANAGED = "xmpdm:ContributedMediaManaged".hashCode();
    public static final int TAG_XMPDM_CONTRIBUTEDMEDIASTARTTIME = "xmpdm:ContributedMediaStartTime".hashCode();
    public static final int TAG_XMPDM_CONTRIBUTEDMEDIASTARTTIMEVALUE = "xmpdm:ContributedMediaStartTimeValue".hashCode();
    public static final int TAG_XMPDM_CONTRIBUTEDMEDIAWEBSTATEMENT = "xmpdm:ContributedMediaWebStatement".hashCode();
    public static final int TAG_XMPDM_DIRECTOR = "xmpdm:Director".hashCode();
    public static final int TAG_XMPDM_DISCNUMBER = "xmpdm:DiscNumber".hashCode();
    public static final int TAG_XMPDM_DURATIONSCALE = "xmpdm:DurationScale".hashCode();
    public static final int TAG_XMPDM_ENGINEER = "xmpdm:Engineer".hashCode();
    public static final int TAG_XMPDM_GENRE = "xmpdm:Genre".hashCode();
    public static final int TAG_XMPDM_INSTRUMENT = "xmpdm:Instrument".hashCode();
    public static final int TAG_XMPDM_INTROTIMESCALE = "xmpdm:IntroTimeScale".hashCode();
    public static final int TAG_XMPDM_KEY = "xmpdm:Key".hashCode();
    public static final int TAG_XMPDM_LOOP = "xmpdm:Loop".hashCode();
    public static final int TAG_XMPDM_MARKERS = "xmpdm:Markers".hashCode();
    public static final int TAG_XMPDM_MARKERSCUEPOINTPARAMS = "xmpdm:MarkersCuePointParams".hashCode();
    public static final int TAG_XMPDM_MARKERSCUEPOINTPARAMSVALUE = "xmpdm:MarkersCuePointParamsValue".hashCode();
    public static final int TAG_XMPDM_MARKERSDURATION = "xmpdm:MarkersDuration".hashCode();
    public static final int TAG_XMPDM_MARKERSNAME = "xmpdm:MarkersName".hashCode();
    public static final int TAG_XMPDM_MARKERSSPEAKER = "xmpdm:MarkersSpeaker".hashCode();
    public static final int TAG_XMPDM_MARKERSTARGET = "xmpdm:MarkersTarget".hashCode();
    public static final int TAG_XMPDM_METADATAMODDATE = "xmpdm:MetadataModDate".hashCode();
    public static final int TAG_XMPDM_OUTCUE = "xmpdm:OutCue".hashCode();
    public static final int TAG_XMPDM_OUTCUEVALUE = "xmpdm:OutCueValue".hashCode();
    public static final int TAG_XMPDM_PROJECTNAME = "xmpdm:ProjectName".hashCode();
    public static final int TAG_XMPDM_PROJECTREFPATH = "xmpdm:ProjectRefPath".hashCode();
    public static final int TAG_XMPDM_PULLDOWN = "xmpdm:PullDown".hashCode();
    public static final int TAG_XMPDM_RELATIVETIMESTAMP = "xmpdm:RelativeTimestamp".hashCode();
    public static final int TAG_XMPDM_RELATIVETIMESTAMPVALUE = "xmpdm:RelativeTimestampValue".hashCode();
    public static final int TAG_XMPDM_RESAMPLEPARAMS = "xmpdm:ResampleParams".hashCode();
    public static final int TAG_XMPDM_SCALETYPE = "xmpdm:ScaleType".hashCode();
    public static final int TAG_XMPDM_SHOTDATE = "xmpdm:ShotDate".hashCode();
    public static final int TAG_XMPDM_SHOTLOCATION = "xmpdm:ShotLocation".hashCode();
    public static final int TAG_XMPDM_SHOTNUMBER = "xmpdm:ShotNumber".hashCode();
    public static final int TAG_XMPDM_SPEAKERPLACEMENT = "xmpdm:SpeakerPlacement".hashCode();
    public static final int TAG_XMPDM_STARTTIMECODETIMEFORMAT = "xmpdm:StartTimecodeTimeFormat".hashCode();
    public static final int TAG_XMPDM_STARTTIMECODEVALUE = "xmpdm:StartTimecodeValue".hashCode();
    public static final int TAG_XMPDM_STARTTIMESCALE = "xmpdm:StartTimeScale".hashCode();
    public static final int TAG_XMPDM_TAKENUMBER = "xmpdm:TakeNumber".hashCode();
    public static final int TAG_XMPDM_TEMPO = "xmpdm:Tempo".hashCode();
    public static final int TAG_XMPDM_TIMESCALEPARAMSFRAMEOVERLAPPINGPERCENTAGE = "xmpdm:TimeScaleParamsFrameOverlappingPercentage".hashCode();
    public static final int TAG_XMPDM_TIMESCALEPARAMSQUALITY = "xmpdm:TimeScaleParamsQuality".hashCode();
    public static final int TAG_XMPDM_TRACKNUMBER = "xmpdm:TrackNumber".hashCode();
    public static final int TAG_XMPDM_TRACKSFRAMERATE = "xmpdm:TracksFrameRate".hashCode();
    public static final int TAG_XMPDM_TRACKSMARKERSCOMMENT = "xmpdm:TracksMarkersComment".hashCode();
    public static final int TAG_XMPDM_TRACKSMARKERSCUEPOINTPARAMSKEY = "xmpdm:TracksMarkersCuePointParamsKey".hashCode();
    public static final int TAG_XMPDM_TRACKSMARKERSCUEPOINTTYPE = "xmpdm:TracksMarkersCuePointType".hashCode();
    public static final int TAG_XMPDM_TRACKSMARKERSLOCATION = "xmpdm:TracksMarkersLocation".hashCode();
    public static final int TAG_XMPDM_TRACKSMARKERSPROBABILITY = "xmpdm:TracksMarkersProbability".hashCode();
    public static final int TAG_XMPDM_TRACKSMARKERSSTARTTIME = "xmpdm:TracksMarkersStartTime".hashCode();
    public static final int TAG_XMPDM_TRACKSMARKERSTYPE = "xmpdm:TracksMarkersType".hashCode();
    public static final int TAG_XMPDM_TRACKSTRACKTYPE = "xmpdm:TracksTrackType".hashCode();
    public static final int TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLOR = "xmpdm:VideoAlphaPremultipleColor".hashCode();
    public static final int TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORB = "xmpdm:VideoAlphaPremultipleColorB".hashCode();
    public static final int TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORBLUE = "xmpdm:VideoAlphaPremultipleColorBlue".hashCode();
    public static final int TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORGRAY = "xmpdm:VideoAlphaPremultipleColorGray".hashCode();
    public static final int TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORL = "xmpdm:VideoAlphaPremultipleColorL".hashCode();
    public static final int TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORMODE = "xmpdm:VideoAlphaPremultipleColorMode".hashCode();
    public static final int TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORSWATCHNAME = "xmpdm:VideoAlphaPremultipleColorSwatchName".hashCode();
    public static final int TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORTYPE = "xmpdm:VideoAlphaPremultipleColorType".hashCode();
    public static final int TAG_XMPDM_VIDEOALPHAUNITYISTRANSPARENT = "xmpdm:VideoAlphaUnityIsTransparent".hashCode();
    public static final int TAG_XMPDM_VIDEOCOMPRESSOR = "xmpdm:VideoCompressor".hashCode();
    public static final int TAG_XMPDM_VIDEOFRAMERATE = "xmpdm:VideoFrameRate".hashCode();
    public static final int TAG_XMPDM_VIDEOFRAMESIZEH = "xmpdm:VideoFrameSizeH".hashCode();
    public static final int TAG_XMPDM_VIDEOFRAMESIZEW = "xmpdm:VideoFrameSizeW".hashCode();
    public static final int TAG_XMPDM_VIDEOPIXELASPECTRATIO = "xmpdm:VideoPixelAspectRatio".hashCode();

    // xmpMM
    public static final int TAG_XMPMM_DERIVEDFROM = "xmpmm:DerivedFrom".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMDOCUMENTID = "xmpmm:DerivedFromDocumentID".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMFROMPART = "xmpmm:DerivedFromFromPart".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMLASTMODIFYDATE = "xmpmm:DerivedFromLastModifyDate".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMLINKCATEGORY = "xmpmm:DerivedFromLinkCategory".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMMANAGER = "xmpmm:DerivedFromManager".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMMANAGETO = "xmpmm:DerivedFromManageTo".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMMASKMARKERS = "xmpmm:DerivedFromMaskMarkers".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMPARTMAPPING = "xmpmm:DerivedFromPartMapping".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMPLACEDXRESOLUTION = "xmpmm:DerivedFromPlacedXResolution".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMRENDITIONCLASS = "xmpmm:DerivedFromRenditionClass".hashCode();
    public static final int TAG_XMPMM_DERIVEDFROMTOPART = "xmpmm:DerivedFromToPart".hashCode();
    public static final int TAG_XMPMM_DOCUMENTID = "xmpmm:DocumentID".hashCode();
    public static final int TAG_XMPMM_HISTORYACTION = "xmpmm:HistoryAction".hashCode();
    public static final int TAG_XMPMM_HISTORYINSTANCEID = "xmpmm:HistoryInstanceID".hashCode();
    public static final int TAG_XMPMM_HISTORYSOFTWAREAGENT = "xmpmm:HistorySoftwareAgent".hashCode();
    public static final int TAG_XMPMM_INGREDIENTS = "xmpmm:Ingredients".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSDOCUMENTID = "xmpmm:IngredientsDocumentID".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSFROMPART = "xmpmm:IngredientsFromPart".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSLASTMODIFYDATE = "xmpmm:IngredientsLastModifyDate".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSLINKCATEGORY = "xmpmm:IngredientsLinkCategory".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSMANAGER = "xmpmm:IngredientsManager".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSMANAGETO = "xmpmm:IngredientsManageTo".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSMASKMARKERS = "xmpmm:IngredientsMaskMarkers".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSPARTMAPPING = "xmpmm:IngredientsPartMapping".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSPLACEDXRESOLUTION = "xmpmm:IngredientsPlacedXResolution".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSRENDITIONCLASS = "xmpmm:IngredientsRenditionClass".hashCode();
    public static final int TAG_XMPMM_INGREDIENTSTOPART = "xmpmm:IngredientsToPart".hashCode();
    public static final int TAG_XMPMM_INSTANCEID = "xmpmm:InstanceID".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROM = "xmpmm:ManagedFrom".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMDOCUMENTID = "xmpmm:ManagedFromDocumentID".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMFROMPART = "xmpmm:ManagedFromFromPart".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMLASTMODIFYDATE = "xmpmm:ManagedFromLastModifyDate".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMLINKCATEGORY = "xmpmm:ManagedFromLinkCategory".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMMANAGER = "xmpmm:ManagedFromManager".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMMANAGETO = "xmpmm:ManagedFromManageTo".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMMASKMARKERS = "xmpmm:ManagedFromMaskMarkers".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMPARTMAPPING = "xmpmm:ManagedFromPartMapping".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMPLACEDXRESOLUTION = "xmpmm:ManagedFromPlacedXResolution".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMRENDITIONCLASS = "xmpmm:ManagedFromRenditionClass".hashCode();
    public static final int TAG_XMPMM_MANAGEDFROMTOPART = "xmpmm:ManagedFromToPart".hashCode();
    public static final int TAG_XMPMM_MANAGER = "xmpmm:Manager".hashCode();
    public static final int TAG_XMPMM_MANAGETO = "xmpmm:ManageTo".hashCode();
    public static final int TAG_XMPMM_MANIFEST = "xmpmm:Manifest".hashCode();
    public static final int TAG_XMPMM_MANIFESTPLACEDRESOLUTIONUNIT = "xmpmm:ManifestPlacedResolutionUnit".hashCode();
    public static final int TAG_XMPMM_MANIFESTPLACEDYRESOLUTION = "xmpmm:ManifestPlacedYResolution".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCEALTERNATEPATHS = "xmpmm:ManifestReferenceAlternatePaths".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCEFILEPATH = "xmpmm:ManifestReferenceFilePath".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCEINSTANCEID = "xmpmm:ManifestReferenceInstanceID".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCELASTURL = "xmpmm:ManifestReferenceLastURL".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCELINKFORM = "xmpmm:ManifestReferenceLinkForm".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCEMANAGERVARIANT = "xmpmm:ManifestReferenceManagerVariant".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCEMANAGEUI = "xmpmm:ManifestReferenceManageUI".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCEORIGINALDOCUMENTID = "xmpmm:ManifestReferenceOriginalDocumentID".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCEPLACEDRESOLUTIONUNIT = "xmpmm:ManifestReferencePlacedResolutionUnit".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCEPLACEDYRESOLUTION = "xmpmm:ManifestReferencePlacedYResolution".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCERENDITIONPARAMS = "xmpmm:ManifestReferenceRenditionParams".hashCode();
    public static final int TAG_XMPMM_MANIFESTREFERENCEVERSIONID = "xmpmm:ManifestReferenceVersionID".hashCode();
    public static final int TAG_XMPMM_PANTRY = "xmpmm:Pantry".hashCode();
    public static final int TAG_XMPMM_RENDITIONCLASS = "xmpmm:RenditionClass".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFALTERNATEPATHS = "xmpmm:RenditionOfAlternatePaths".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFFILEPATH = "xmpmm:RenditionOfFilePath".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFINSTANCEID = "xmpmm:RenditionOfInstanceID".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFLASTURL = "xmpmm:RenditionOfLastURL".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFLINKFORM = "xmpmm:RenditionOfLinkForm".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFMANAGERVARIANT = "xmpmm:RenditionOfManagerVariant".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFMANAGEUI = "xmpmm:RenditionOfManageUI".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFORIGINALDOCUMENTID = "xmpmm:RenditionOfOriginalDocumentID".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFPLACEDRESOLUTIONUNIT = "xmpmm:RenditionOfPlacedResolutionUnit".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFPLACEDYRESOLUTION = "xmpmm:RenditionOfPlacedYResolution".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFRENDITIONPARAMS = "xmpmm:RenditionOfRenditionParams".hashCode();
    public static final int TAG_XMPMM_RENDITIONOFVERSIONID = "xmpmm:RenditionOfVersionID".hashCode();
    public static final int TAG_XMPMM_SAVEID = "xmpmm:SaveID".hashCode();
    public static final int TAG_XMPMM_VERSIONID = "xmpmm:VersionID".hashCode();
    public static final int TAG_XMPMM_VERSIONSCOMMENTS = "xmpmm:VersionsComments".hashCode();
    public static final int TAG_XMPMM_VERSIONSEVENTACTION = "xmpmm:VersionsEventAction".hashCode();
    public static final int TAG_XMPMM_VERSIONSEVENTINSTANCEID = "xmpmm:VersionsEventInstanceID".hashCode();
    public static final int TAG_XMPMM_VERSIONSEVENTSOFTWAREAGENT = "xmpmm:VersionsEventSoftwareAgent".hashCode();
    public static final int TAG_XMPMM_VERSIONSMODIFIER = "xmpmm:VersionsModifier".hashCode();
    public static final int TAG_XMPMM_VERSIONSVERSION = "xmpmm:VersionsVersion".hashCode();

    // xmpNote
    public static final int TAG_XMPNOTE_HASEXTENDEDXMP = "xmpnote:HasExtendedXMP".hashCode();

    // xmpPLUS
    public static final int TAG_XMPPLUS_CREDITLINEREQ = "xmpplus:CreditLineReq".hashCode();

    // xmpRights
    public static final int TAG_XMPRIGHTS_CERTIFICATE = "xmprights:Certificate".hashCode();
    public static final int TAG_XMPRIGHTS_OWNER = "xmprights:Owner".hashCode();
    public static final int TAG_XMPRIGHTS_WEBSTATEMENT = "xmprights:WebStatement".hashCode();

    // xmpTPg
    public static final int TAG_XMPTPG_COLORANTS = "xmptpg:Colorants".hashCode();
    public static final int TAG_XMPTPG_COLORANTB = "xmptpg:ColorantB".hashCode();
    public static final int TAG_XMPTPG_COLORANTBLUE = "xmptpg:ColorantBlue".hashCode();
    public static final int TAG_XMPTPG_COLORANTGRAY = "xmptpg:ColorantGray".hashCode();
    public static final int TAG_XMPTPG_COLORANTL = "xmptpg:ColorantL".hashCode();
    public static final int TAG_XMPTPG_COLORANTMODE = "xmptpg:ColorantMode".hashCode();
    public static final int TAG_XMPTPG_COLORANTSWATCHNAME = "xmptpg:ColorantSwatchName".hashCode();
    public static final int TAG_XMPTPG_COLORANTTYPE = "xmptpg:ColorantType".hashCode();
    public static final int TAG_XMPTPG_FONTS = "xmptpg:Fonts".hashCode();
    public static final int TAG_XMPTPG_FONTCOMPOSITE = "xmptpg:FontComposite".hashCode();
    public static final int TAG_XMPTPG_FONTFAMILY = "xmptpg:FontFamily".hashCode();
    public static final int TAG_XMPTPG_FONTNAME = "xmptpg:FontName".hashCode();
    public static final int TAG_XMPTPG_FONTVERSION = "xmptpg:FontVersion".hashCode();
    public static final int TAG_XMPTPG_HASVISIBLETRANSPARENCY = "xmptpg:HasVisibleTransparency".hashCode();
    public static final int TAG_XMPTPG_MAXPAGESIZEH = "xmptpg:MaxPageSizeH".hashCode();
    public static final int TAG_XMPTPG_MAXPAGESIZEW = "xmptpg:MaxPageSizeW".hashCode();
    public static final int TAG_XMPTPG_PLATENAMES = "xmptpg:PlateNames".hashCode();
    public static final int TAG_XMPTPG_SWATCHGROUPSCOLORANTS = "xmptpg:SwatchGroupsColorants".hashCode();
    public static final int TAG_XMPTPG_SWATCHCOLORANTB = "xmptpg:SwatchColorantB".hashCode();
    public static final int TAG_XMPTPG_SWATCHCOLORANTBLUE = "xmptpg:SwatchColorantBlue".hashCode();
    public static final int TAG_XMPTPG_SWATCHCOLORANTGRAY = "xmptpg:SwatchColorantGray".hashCode();
    public static final int TAG_XMPTPG_SWATCHCOLORANTL = "xmptpg:SwatchColorantL".hashCode();
    public static final int TAG_XMPTPG_SWATCHCOLORANTMODE = "xmptpg:SwatchColorantMode".hashCode();
    public static final int TAG_XMPTPG_SWATCHCOLORANTSWATCHNAME = "xmptpg:SwatchColorantSwatchName".hashCode();
    public static final int TAG_XMPTPG_SWATCHCOLORANTTYPE = "xmptpg:SwatchColorantType".hashCode();
    public static final int TAG_XMPTPG_SWATCHGROUPNAME = "xmptpg:SwatchGroupName".hashCode();

    // XML
    public static final int TAG_XML_DC = "xml:dc".hashCode();

    // SVG
    public static final int TAG_SVG_HEIGHT = "svg:height".hashCode();
    public static final int TAG_SVG_METADATAID = "svg:metadataId".hashCode();
    public static final int TAG_SVG_WIDTH = "svg:width".hashCode();

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static {
        _tagNameMap.put(TAG_XMP_VALUE_COUNT, "XMP Value Count");
        // aas
        _tagNameMap.put(TAG_AAS_AFFINEA, "AffineA");
        _tagNameMap.put(TAG_AAS_AFFINEC, "AffineC");
        _tagNameMap.put(TAG_AAS_AFFINEX, "AffineX");
        _tagNameMap.put(TAG_AAS_CROPH, "CropH");
        _tagNameMap.put(TAG_AAS_CROPX, "CropX");
        _tagNameMap.put(TAG_AAS_CURVE0X, "Curve0x");
        _tagNameMap.put(TAG_AAS_CURVE1X, "Curve1x");
        _tagNameMap.put(TAG_AAS_CURVE2X, "Curve2x");
        _tagNameMap.put(TAG_AAS_CURVE3X, "Curve3x");
        _tagNameMap.put(TAG_AAS_CURVE4X, "Curve4x");
        _tagNameMap.put(TAG_AAS_FACEBALANCEORIGI, "FaceBalanceOrigI");
        _tagNameMap.put(TAG_AAS_FACEBALANCESTRENGTH, "FaceBalanceStrength");
        _tagNameMap.put(TAG_AAS_HIGHLIGHTS, "Highlights");
        _tagNameMap.put(TAG_AAS_VIBRANCE, "Vibrance");

        // acdsee
        _tagNameMap.put(TAG_ACDSEE_AUTHOR, "Author");
        _tagNameMap.put(TAG_ACDSEE_CATEGORIES, "Categories");
        _tagNameMap.put(TAG_ACDSEE_DATETIME, "DateTime");
        _tagNameMap.put(TAG_ACDSEE_EDITSTATUS, "EditStatus");
        _tagNameMap.put(TAG_ACDSEE_KEYWORDS, "Keywords");
        _tagNameMap.put(TAG_ACDSEE_OBJECTCYCLE, "ObjectCycle");
        _tagNameMap.put(TAG_ACDSEE_RATING, "Rating");
        _tagNameMap.put(TAG_ACDSEE_RELEASEDATE, "ReleaseDate");
        _tagNameMap.put(TAG_ACDSEE_RPP, "RPP");
        _tagNameMap.put(TAG_ACDSEE_TAGGED, "Tagged");

        // Album
        _tagNameMap.put(TAG_ALBUM_NOTES, "Notes");

        // apple_fi
        _tagNameMap.put(TAG_APPLE_FI_ANGLEINFOROLL, "AngleInfoRoll");
        _tagNameMap.put(TAG_APPLE_FI_CONFIDENCELEVEL, "ConfidenceLevel");
        _tagNameMap.put(TAG_APPLE_FI_TIMESTAMP, "TimeStamp");

        // aux
        _tagNameMap.put(TAG_AUX_APPROXIMATEFOCUSDISTANCE, "ApproximateFocusDistance");
        _tagNameMap.put(TAG_AUX_FIRMWARE, "Firmware");
        _tagNameMap.put(TAG_AUX_IMAGENUMBER, "ImageNumber");
        _tagNameMap.put(TAG_AUX_ISMERGEDPANORAMA, "IsMergedPanorama");
        _tagNameMap.put(TAG_AUX_LENS, "Lens");
        _tagNameMap.put(TAG_AUX_LENSID, "LensID");
        _tagNameMap.put(TAG_AUX_LENSSERIALNUMBER, "LensSerialNumber");
        _tagNameMap.put(TAG_AUX_SERIALNUMBER, "SerialNumber");

        // cc
        _tagNameMap.put(TAG_CC_ATTRIBUTIONNAME, "AttributionName");
        _tagNameMap.put(TAG_CC_DEPRECATEDON, "DeprecatedOn");
        _tagNameMap.put(TAG_CC_LEGALCODE, "LegalCode");
        _tagNameMap.put(TAG_CC_MOREPERMISSIONS, "MorePermissions");
        _tagNameMap.put(TAG_CC_PROHIBITS, "Prohibits");
        _tagNameMap.put(TAG_CC_USEGUIDELINES, "UseGuidelines");

        // cell
        _tagNameMap.put(TAG_CELL_CELLTOWERID, "CellTowerID");
        _tagNameMap.put(TAG_CELL_LOCATIONAREACODE, "LocationAreaCode");
        _tagNameMap.put(TAG_CELL_MOBILENETWORKCODE, "MobileNetworkCode");

        // creatorAtom
        _tagNameMap.put(TAG_CREATORATOM_AEPROJECTLINK, "AeProjectLink");
        _tagNameMap.put(TAG_CREATORATOM_AEPROJECTLINKFULLPATH, "AeProjectLinkFullPath");
        _tagNameMap.put(TAG_CREATORATOM_AEPROJECTLINKRENDERQUEUEITEMID, "AeProjectLinkRenderQueueItemID");
        _tagNameMap.put(TAG_CREATORATOM_MACATOM, "MacAtom");
        _tagNameMap.put(TAG_CREATORATOM_MACATOMINVOCATIONAPPLEEVENT, "MacAtomInvocationAppleEvent");
        _tagNameMap.put(TAG_CREATORATOM_WINDOWSATOM, "WindowsAtom");
        _tagNameMap.put(TAG_CREATORATOM_WINDOWSATOMINVOCATIONFLAGS, "WindowsAtomInvocationFlags");

        // crs
        _tagNameMap.put(TAG_CRS_ALREADYAPPLIED, "AlreadyApplied");
        _tagNameMap.put(TAG_CRS_AUTOCONTRAST, "AutoContrast");
        _tagNameMap.put(TAG_CRS_AUTOLATERALCA, "AutoLateralCA");
        _tagNameMap.put(TAG_CRS_AUTOWHITEVERSION, "AutoWhiteVersion");
        _tagNameMap.put(TAG_CRS_BLUEHUE, "BlueHue");
        _tagNameMap.put(TAG_CRS_BRIGHTNESS, "Brightness");
        _tagNameMap.put(TAG_CRS_CAMERAPROFILEDIGEST, "CameraProfileDigest");
        _tagNameMap.put(TAG_CRS_CHROMATICABERRATIONR, "ChromaticAberrationR");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRACTIVE, "CircGradBasedCorrActive");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKS, "CircGradBasedCorrMasks");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKANGLE, "CircGradBasedCorrMaskAngle");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKCENTERVALUE, "CircGradBasedCorrMaskCenterValue");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKDABS, "CircGradBasedCorrMaskDabs");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKFLIPPED, "CircGradBasedCorrMaskFlipped");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKFULLX, "CircGradBasedCorrMaskFullX");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKLEFT, "CircGradBasedCorrMaskLeft");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKMIDPOINT, "CircGradBasedCorrMaskMidpoint");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKRADIUS, "CircGradBasedCorrMaskRadius");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKROUNDNESS, "CircGradBasedCorrMaskRoundness");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKSIZEY, "CircGradBasedCorrMaskSizeY");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKVERSION, "CircGradBasedCorrMaskVersion");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKX, "CircGradBasedCorrMaskX");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMASKZEROX, "CircGradBasedCorrMaskZeroX");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRBRIGHTNESS, "CircGradBasedCorrBrightness");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRCLARITY2012, "CircGradBasedCorrClarity2012");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRCONTRAST2012, "CircGradBasedCorrContrast2012");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORREXPOSURE, "CircGradBasedCorrExposure");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRHIGHLIGHTS2012, "CircGradBasedCorrHighlights2012");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRMOIRE, "CircGradBasedCorrMoire");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRSHADOWS2012, "CircGradBasedCorrShadows2012");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRTEMPERATURE, "CircGradBasedCorrTemperature");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRHUE, "CircGradBasedCorrHue");
        _tagNameMap.put(TAG_CRS_CIRCGRADBASEDCORRWHAT, "CircGradBasedCorrWhat");
        _tagNameMap.put(TAG_CRS_CLARITY2012, "Clarity2012");
        _tagNameMap.put(TAG_CRS_COLORNOISEREDUCTIONDETAIL, "ColorNoiseReductionDetail");
        _tagNameMap.put(TAG_CRS_CONTRAST, "Contrast");
        _tagNameMap.put(TAG_CRS_CONVERTER, "Converter");
        _tagNameMap.put(TAG_CRS_CROPANGLE, "CropAngle");
        _tagNameMap.put(TAG_CRS_CROPCONSTRAINTOWARP, "CropConstrainToWarp");
        _tagNameMap.put(TAG_CRS_CROPLEFT, "CropLeft");
        _tagNameMap.put(TAG_CRS_CROPTOP, "CropTop");
        _tagNameMap.put(TAG_CRS_CROPUNITS, "CropUnits");
        _tagNameMap.put(TAG_CRS_DEFAULTAUTOGRAY, "DefaultAutoGray");
        _tagNameMap.put(TAG_CRS_DEFAULTSSPECIFICTOISO, "DefaultsSpecificToISO");
        _tagNameMap.put(TAG_CRS_DEFRINGE, "Defringe");
        _tagNameMap.put(TAG_CRS_DEFRINGEGREENHUEHI, "DefringeGreenHueHi");
        _tagNameMap.put(TAG_CRS_DEFRINGEPURPLEAMOUNT, "DefringePurpleAmount");
        _tagNameMap.put(TAG_CRS_DEFRINGEPURPLEHUELO, "DefringePurpleHueLo");
        _tagNameMap.put(TAG_CRS_DNGIGNORESIDECARS, "DNGIgnoreSidecars");
        _tagNameMap.put(TAG_CRS_EXPOSURE2012, "Exposure2012");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRECTIONS, "GradientBasedCorrections");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRAMOUNT, "GradientBasedCorrAmount");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKALPHA, "GradientBasedCorrMaskAlpha");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKBOTTOM, "GradientBasedCorrMaskBottom");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKCENTERWEIGHT, "GradientBasedCorrMaskCenterWeight");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKFEATHER, "GradientBasedCorrMaskFeather");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKFLOW, "GradientBasedCorrMaskFlow");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKFULLY, "GradientBasedCorrMaskFullY");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKVALUE, "GradientBasedCorrMaskValue");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKPERIMETERVALUE, "GradientBasedCorrMaskPerimeterValue");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKRIGHT, "GradientBasedCorrMaskRight");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKSIZEX, "GradientBasedCorrMaskSizeX");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKTOP, "GradientBasedCorrMaskTop");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKWHAT, "GradientBasedCorrMaskWhat");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKY, "GradientBasedCorrMaskY");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRMASKZEROY, "GradientBasedCorrMaskZeroY");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRCLARITY, "GradientBasedCorrClarity");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRCONTRAST, "GradientBasedCorrContrast");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRDEFRINGE, "GradientBasedCorrDefringe");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORREXPOSURE2012, "GradientBasedCorrExposure2012");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRLUMINANCENOISE, "GradientBasedCorrLuminanceNoise");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRSATURATION, "GradientBasedCorrSaturation");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRSHARPNESS, "GradientBasedCorrSharpness");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRTINT, "GradientBasedCorrTint");
        _tagNameMap.put(TAG_CRS_GRADIENTBASEDCORRSATURATION, "GradientBasedCorrSaturation");
        _tagNameMap.put(TAG_CRS_GRAINAMOUNT, "GrainAmount");
        _tagNameMap.put(TAG_CRS_GRAINSIZE, "GrainSize");
        _tagNameMap.put(TAG_CRS_GRAYMIXERBLUE, "GrayMixerBlue");
        _tagNameMap.put(TAG_CRS_GRAYMIXERMAGENTA, "GrayMixerMagenta");
        _tagNameMap.put(TAG_CRS_GRAYMIXERPURPLE, "GrayMixerPurple");
        _tagNameMap.put(TAG_CRS_GRAYMIXERYELLOW, "GrayMixerYellow");
        _tagNameMap.put(TAG_CRS_GREENSATURATION, "GreenSaturation");
        _tagNameMap.put(TAG_CRS_HASSETTINGS, "HasSettings");
        _tagNameMap.put(TAG_CRS_HIGHLIGHTS2012, "Highlights2012");
        _tagNameMap.put(TAG_CRS_HUEADJUSTMENTBLUE, "HueAdjustmentBlue");
        _tagNameMap.put(TAG_CRS_HUEADJUSTMENTMAGENTA, "HueAdjustmentMagenta");
        _tagNameMap.put(TAG_CRS_HUEADJUSTMENTPURPLE, "HueAdjustmentPurple");
        _tagNameMap.put(TAG_CRS_HUEADJUSTMENTYELLOW, "HueAdjustmentYellow");
        _tagNameMap.put(TAG_CRS_INCREMENTALTINT, "IncrementalTint");
        _tagNameMap.put(TAG_CRS_LENSMANUALDISTORTIONAMOUNT, "LensManualDistortionAmount");
        _tagNameMap.put(TAG_CRS_LENSPROFILEDIGEST, "LensProfileDigest");
        _tagNameMap.put(TAG_CRS_LENSPROFILEENABLE, "LensProfileEnable");
        _tagNameMap.put(TAG_CRS_LENSPROFILEMATCHKEYCAMERAMODELNAME, "LensProfileMatchKeyCameraModelName");
        _tagNameMap.put(TAG_CRS_LENSPROFILEMATCHKEYEXIFMODEL, "LensProfileMatchKeyExifModel");
        _tagNameMap.put(TAG_CRS_LENSPROFILEMATCHKEYLENSID, "LensProfileMatchKeyLensID");
        _tagNameMap.put(TAG_CRS_LENSPROFILEMATCHKEYLENSNAME, "LensProfileMatchKeyLensName");
        _tagNameMap.put(TAG_CRS_LENSPROFILENAME, "LensProfileName");
        _tagNameMap.put(TAG_CRS_LENSPROFILEVIGNETTINGSCALE, "LensProfileVignettingScale");
        _tagNameMap.put(TAG_CRS_LUMINANCEADJUSTMENTBLUE, "LuminanceAdjustmentBlue");
        _tagNameMap.put(TAG_CRS_LUMINANCEADJUSTMENTMAGENTA, "LuminanceAdjustmentMagenta");
        _tagNameMap.put(TAG_CRS_LUMINANCEADJUSTMENTPURPLE, "LuminanceAdjustmentPurple");
        _tagNameMap.put(TAG_CRS_LUMINANCEADJUSTMENTYELLOW, "LuminanceAdjustmentYellow");
        _tagNameMap.put(TAG_CRS_LUMINANCENOISEREDUCTIONDETAIL, "LuminanceNoiseReductionDetail");
        _tagNameMap.put(TAG_CRS_MOIREFILTER, "MoireFilter");
        _tagNameMap.put(TAG_CRS_NEGATIVECACHEMAXIMUMSIZE, "NegativeCacheMaximumSize");
        _tagNameMap.put(TAG_CRS_PAINTBASEDCORRECTIONS, "PaintBasedCorrections");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONAMOUNT, "PaintCorrectionAmount");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKALPHA, "PaintCorrectionMaskAlpha");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKBOTTOM, "PaintCorrectionMaskBottom");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKCENTERWEIGHT, "PaintCorrectionMaskCenterWeight");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKFEATHER, "PaintCorrectionMaskFeather");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKFLOW, "PaintCorrectionMaskFlow");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKFULLY, "PaintCorrectionMaskFullY");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKVALUE, "PaintCorrectionMaskValue");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKPERIMETERVALUE, "PaintCorrectionMaskPerimeterValue");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKRIGHT, "PaintCorrectionMaskRight");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKSIZEX, "PaintCorrectionMaskSizeX");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKTOP, "PaintCorrectionMaskTop");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKWHAT, "PaintCorrectionMaskWhat");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKY, "PaintCorrectionMaskY");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONMASKZEROY, "PaintCorrectionMaskZeroY");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONCLARITY, "PaintCorrectionClarity");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONCONTRAST, "PaintCorrectionContrast");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONDEFRINGE, "PaintCorrectionDefringe");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONEXPOSURE2012, "PaintCorrectionExposure2012");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONLUMINANCENOISE, "PaintCorrectionLuminanceNoise");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONSATURATION, "PaintCorrectionSaturation");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONSHARPNESS, "PaintCorrectionSharpness");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONTINT, "PaintCorrectionTint");
        _tagNameMap.put(TAG_CRS_PAINTCORRECTIONSATURATION, "PaintCorrectionSaturation");
        _tagNameMap.put(TAG_CRS_PARAMETRICDARKS, "ParametricDarks");
        _tagNameMap.put(TAG_CRS_PARAMETRICHIGHLIGHTSPLIT, "ParametricHighlightSplit");
        _tagNameMap.put(TAG_CRS_PARAMETRICMIDTONESPLIT, "ParametricMidtoneSplit");
        _tagNameMap.put(TAG_CRS_PARAMETRICSHADOWSPLIT, "ParametricShadowSplit");
        _tagNameMap.put(TAG_CRS_PERSPECTIVEHORIZONTAL, "PerspectiveHorizontal");
        _tagNameMap.put(TAG_CRS_PERSPECTIVESCALE, "PerspectiveScale");
        _tagNameMap.put(TAG_CRS_PERSPECTIVEVERTICAL, "PerspectiveVertical");
        _tagNameMap.put(TAG_CRS_PERSPECTIVEY, "PerspectiveY");
        _tagNameMap.put(TAG_CRS_POSTCROPVIGNETTEFEATHER, "PostCropVignetteFeather");
        _tagNameMap.put(TAG_CRS_POSTCROPVIGNETTEMIDPOINT, "PostCropVignetteMidpoint");
        _tagNameMap.put(TAG_CRS_POSTCROPVIGNETTESTYLE, "PostCropVignetteStyle");
        _tagNameMap.put(TAG_CRS_RAWFILENAME, "RawFileName");
        _tagNameMap.put(TAG_CRS_REDHUE, "RedHue");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAS, "RetouchAreas");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKS, "RetouchAreaMasks");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKANGLE, "RetouchAreaMaskAngle");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKCENTERVALUE, "RetouchAreaMaskCenterValue");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKDABS, "RetouchAreaMaskDabs");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKFLIPPED, "RetouchAreaMaskFlipped");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKFULLX, "RetouchAreaMaskFullX");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKLEFT, "RetouchAreaMaskLeft");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKMIDPOINT, "RetouchAreaMaskMidpoint");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKRADIUS, "RetouchAreaMaskRadius");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKROUNDNESS, "RetouchAreaMaskRoundness");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKSIZEY, "RetouchAreaMaskSizeY");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKVERSION, "RetouchAreaMaskVersion");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKX, "RetouchAreaMaskX");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMASKZEROX, "RetouchAreaMaskZeroX");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAMETHOD, "RetouchAreaMethod");
        _tagNameMap.put(TAG_CRS_RETOUCHAREAOPACITY, "RetouchAreaOpacity");
        _tagNameMap.put(TAG_CRS_RETOUCHAREASOURCESTATE, "RetouchAreaSourceState");
        _tagNameMap.put(TAG_CRS_RETOUCHAREASPOTTYPE, "RetouchAreaSpotType");
        _tagNameMap.put(TAG_CRS_SATURATION, "Saturation");
        _tagNameMap.put(TAG_CRS_SATURATIONADJUSTMENTBLUE, "SaturationAdjustmentBlue");
        _tagNameMap.put(TAG_CRS_SATURATIONADJUSTMENTMAGENTA, "SaturationAdjustmentMagenta");
        _tagNameMap.put(TAG_CRS_SATURATIONADJUSTMENTPURPLE, "SaturationAdjustmentPurple");
        _tagNameMap.put(TAG_CRS_SATURATIONADJUSTMENTYELLOW, "SaturationAdjustmentYellow");
        _tagNameMap.put(TAG_CRS_SHADOWS2012, "Shadows2012");
        _tagNameMap.put(TAG_CRS_SHARPENDETAIL, "SharpenDetail");
        _tagNameMap.put(TAG_CRS_SHARPENRADIUS, "SharpenRadius");
        _tagNameMap.put(TAG_CRS_SMOOTHNESS, "Smoothness");
        _tagNameMap.put(TAG_CRS_SPLITTONINGHIGHLIGHTHUE, "SplitToningHighlightHue");
        _tagNameMap.put(TAG_CRS_SPLITTONINGSHADOWHUE, "SplitToningShadowHue");
        _tagNameMap.put(TAG_CRS_COLORTEMPERATURE, "ColorTemperature");
        _tagNameMap.put(TAG_CRS_TINT, "Tint");
        _tagNameMap.put(TAG_CRS_TONECURVEBLUE, "ToneCurveBlue");
        _tagNameMap.put(TAG_CRS_TONECURVENAME, "ToneCurveName");
        _tagNameMap.put(TAG_CRS_TONECURVEPV2012, "ToneCurvePV2012");
        _tagNameMap.put(TAG_CRS_TONECURVEPV2012GREEN, "ToneCurvePV2012Green");
        _tagNameMap.put(TAG_CRS_TONECURVERED, "ToneCurveRed");
        _tagNameMap.put(TAG_CRS_UPRIGHTCENTERMODE, "UprightCenterMode");
        _tagNameMap.put(TAG_CRS_UPRIGHTCENTERNORMY, "UprightCenterNormY");
        _tagNameMap.put(TAG_CRS_UPRIGHTFOCALLENGTH35MM, "UprightFocalLength35mm");
        _tagNameMap.put(TAG_CRS_UPRIGHTFOURSEGMENTSCOUNT, "UprightFourSegmentsCount");
        _tagNameMap.put(TAG_CRS_UPRIGHTTRANSFORM_0, "UprightTransform_0");
        _tagNameMap.put(TAG_CRS_UPRIGHTTRANSFORM_2, "UprightTransform_2");
        _tagNameMap.put(TAG_CRS_UPRIGHTTRANSFORM_4, "UprightTransform_4");
        _tagNameMap.put(TAG_CRS_UPRIGHTTRANSFORMCOUNT, "UprightTransformCount");
        _tagNameMap.put(TAG_CRS_VERSION, "Version");
        _tagNameMap.put(TAG_CRS_VIGNETTEAMOUNT, "VignetteAmount");
        _tagNameMap.put(TAG_CRS_WHAT, "What");
        _tagNameMap.put(TAG_CRS_WHITES2012, "Whites2012");

        // dc
        _tagNameMap.put(TAG_DC_CONTRIBUTOR, "Contributor");
        _tagNameMap.put(TAG_DC_CREATOR, "Creator");
        _tagNameMap.put(TAG_DC_DESCRIPTION, "Description");
        _tagNameMap.put(TAG_DC_IDENTIFIER, "Identifier");
        _tagNameMap.put(TAG_DC_PUBLISHER, "Publisher");
        _tagNameMap.put(TAG_DC_RIGHTS, "Rights");
        _tagNameMap.put(TAG_DC_SUBJECT, "Subject");
        _tagNameMap.put(TAG_DC_TYPE, "Type");

        // dex
        _tagNameMap.put(TAG_DEX_CRC32, "CRC32");
        _tagNameMap.put(TAG_DEX_LICENSETYPE, "LicenseType");
        _tagNameMap.put(TAG_DEX_RATING, "Rating");
        _tagNameMap.put(TAG_DEX_SHORTDESCRIPTION, "ShortDescription");

        // DICOM
        _tagNameMap.put(TAG_DICOM_EQUIPMENTINSTITUTION, "EquipmentInstitution");
        _tagNameMap.put(TAG_DICOM_PATIENTBIRTHDATE, "PatientBirthDate");
        _tagNameMap.put(TAG_DICOM_PATIENTNAME, "PatientName");
        _tagNameMap.put(TAG_DICOM_SERIESDATETIME, "SeriesDateTime");
        _tagNameMap.put(TAG_DICOM_SERIESMODALITY, "SeriesModality");
        _tagNameMap.put(TAG_DICOM_STUDYDATETIME, "StudyDateTime");
        _tagNameMap.put(TAG_DICOM_STUDYID, "StudyID");

        // digiKam
        _tagNameMap.put(TAG_DIGIKAM_CAPTIONSAUTHORNAMES, "CaptionsAuthorNames");
        _tagNameMap.put(TAG_DIGIKAM_COLORLABEL, "ColorLabel");
        _tagNameMap.put(TAG_DIGIKAM_IMAGEUNIQUEID, "ImageUniqueID");
        _tagNameMap.put(TAG_DIGIKAM_PICKLABEL, "PickLabel");

        // exif
        _tagNameMap.put(TAG_EXIF_APERTUREVALUE, "ApertureValue");
        _tagNameMap.put(TAG_EXIF_CFAPATTERN, "CFAPattern");
        _tagNameMap.put(TAG_EXIF_CFAPATTERNROWS, "CFAPatternRows");
        _tagNameMap.put(TAG_EXIF_COLORSPACE, "ColorSpace");
        _tagNameMap.put(TAG_EXIF_COMPRESSEDBITSPERPIXEL, "CompressedBitsPerPixel");
        _tagNameMap.put(TAG_EXIF_CUSTOMRENDERED, "CustomRendered");
        _tagNameMap.put(TAG_EXIF_DATETIMEORIGINAL, "DateTimeOriginal");
        _tagNameMap.put(TAG_EXIF_DEVICESETTINGDESCRIPTIONCOLUMNS, "DeviceSettingDescriptionColumns");
        _tagNameMap.put(TAG_EXIF_DEVICESETTINGDESCRIPTIONSETTINGS, "DeviceSettingDescriptionSettings");
        _tagNameMap.put(TAG_EXIF_EXIFVERSION, "ExifVersion");
        _tagNameMap.put(TAG_EXIF_EXPOSUREINDEX, "ExposureIndex");
        _tagNameMap.put(TAG_EXIF_EXPOSUREPROGRAM, "ExposureProgram");
        _tagNameMap.put(TAG_EXIF_FILESOURCE, "FileSource");
        _tagNameMap.put(TAG_EXIF_FLASHENERGY, "FlashEnergy");
        _tagNameMap.put(TAG_EXIF_FLASHFUNCTION, "FlashFunction");
        _tagNameMap.put(TAG_EXIF_FLASHPIXVERSION, "FlashpixVersion");
        _tagNameMap.put(TAG_EXIF_FLASHRETURN, "FlashReturn");
        _tagNameMap.put(TAG_EXIF_FOCALLENGTH, "FocalLength");
        _tagNameMap.put(TAG_EXIF_FOCALPLANERESOLUTIONUNIT, "FocalPlaneResolutionUnit");
        _tagNameMap.put(TAG_EXIF_FOCALPLANEYRESOLUTION, "FocalPlaneYResolution");
        _tagNameMap.put(TAG_EXIF_GPSALTITUDE, "GPSAltitude");
        _tagNameMap.put(TAG_EXIF_GPSAREAINFORMATION, "GPSAreaInformation");
        _tagNameMap.put(TAG_EXIF_GPSDESTBEARINGREF, "GPSDestBearingRef");
        _tagNameMap.put(TAG_EXIF_GPSDESTDISTANCEREF, "GPSDestDistanceRef");
        _tagNameMap.put(TAG_EXIF_GPSDESTLONGITUDE, "GPSDestLongitude");
        _tagNameMap.put(TAG_EXIF_GPSDOP, "GPSDOP");
        _tagNameMap.put(TAG_EXIF_GPSIMGDIRECTION, "GPSImgDirection");
        _tagNameMap.put(TAG_EXIF_GPSLATITUDE, "GPSLatitude");
        _tagNameMap.put(TAG_EXIF_GPSMAPDATUM, "GPSMapDatum");
        _tagNameMap.put(TAG_EXIF_GPSPROCESSINGMETHOD, "GPSProcessingMethod");
        _tagNameMap.put(TAG_EXIF_GPSSPEED, "GPSSpeed");
        _tagNameMap.put(TAG_EXIF_GPSSTATUS, "GPSStatus");
        _tagNameMap.put(TAG_EXIF_GPSTRACK, "GPSTrack");
        _tagNameMap.put(TAG_EXIF_GPSVERSIONID, "GPSVersionID");
        _tagNameMap.put(TAG_EXIF_ISO, "ISO");
        _tagNameMap.put(TAG_EXIF_MAKERNOTE, "MakerNote");
        _tagNameMap.put(TAG_EXIF_METERINGMODE, "MeteringMode");
        _tagNameMap.put(TAG_EXIF_OPTO_ELECTRICCONVFACTOR, "Opto-ElectricConvFactor");
        _tagNameMap.put(TAG_EXIF_OECFNAMES, "OECFNames");
        _tagNameMap.put(TAG_EXIF_OECFVALUES, "OECFValues");
        _tagNameMap.put(TAG_EXIF_EXIFIMAGEHEIGHT, "ExifImageHeight");
        _tagNameMap.put(TAG_EXIF_SATURATION, "Saturation");
        _tagNameMap.put(TAG_EXIF_SCENETYPE, "SceneType");
        _tagNameMap.put(TAG_EXIF_SHARPNESS, "Sharpness");
        _tagNameMap.put(TAG_EXIF_SPATIALFREQUENCYRESPONSE, "SpatialFrequencyResponse");
        _tagNameMap.put(TAG_EXIF_SPATIALFREQUENCYRESPONSENAMES, "SpatialFrequencyResponseNames");
        _tagNameMap.put(TAG_EXIF_SPATIALFREQUENCYRESPONSEVALUES, "SpatialFrequencyResponseValues");
        _tagNameMap.put(TAG_EXIF_SUBJECTAREA, "SubjectArea");
        _tagNameMap.put(TAG_EXIF_SUBJECTDISTANCERANGE, "SubjectDistanceRange");
        _tagNameMap.put(TAG_EXIF_USERCOMMENT, "UserComment");

        // exifEX
        _tagNameMap.put(TAG_EXIFEX_SERIALNUMBER, "SerialNumber");
        _tagNameMap.put(TAG_EXIFEX_GAMMA, "Gamma");
        _tagNameMap.put(TAG_EXIFEX_ISOSPEED, "ISOSpeed");
        _tagNameMap.put(TAG_EXIFEX_ISOSPEEDLATITUDEZZZ, "ISOSpeedLatitudezzz");
        _tagNameMap.put(TAG_EXIFEX_LENSMODEL, "LensModel");
        _tagNameMap.put(TAG_EXIFEX_LENSINFO, "LensInfo");
        _tagNameMap.put(TAG_EXIFEX_RECOMMENDEDEXPOSUREINDEX, "RecommendedExposureIndex");
        _tagNameMap.put(TAG_EXIFEX_STANDARDOUTPUTSENSITIVITY, "StandardOutputSensitivity");

        // ExpressionMedia
        _tagNameMap.put(TAG_EXPRESSIONMEDIA_CATALOGSETS, "CatalogSets");
        _tagNameMap.put(TAG_EXPRESSIONMEDIA_PEOPLE, "People");

        // extensis
        _tagNameMap.put(TAG_EXTENSIS_APPROVED, "Approved");
        _tagNameMap.put(TAG_EXTENSIS_CLIENTNAME, "ClientName");
        _tagNameMap.put(TAG_EXTENSIS_JOBSTATUS, "JobStatus");
        _tagNameMap.put(TAG_EXTENSIS_ROUTINGNOTES, "RoutingNotes");

        // fpv
        _tagNameMap.put(TAG_FPV_RICHTEXTCOMMENT, "RichTextComment");

        // GAudio
        _tagNameMap.put(TAG_GAUDIO_AUDIODATA, "AudioData");

        // GettyImages
        _tagNameMap.put(TAG_GETTYIMAGES_ASSETID, "AssetID");
        _tagNameMap.put(TAG_GETTYIMAGES_CAMERAFILENAME, "CameraFilename");
        _tagNameMap.put(TAG_GETTYIMAGES_CAMERASERIALNUMBER, "CameraSerialNumber");
        _tagNameMap.put(TAG_GETTYIMAGES_EXCLUSIVECOVERAGE, "ExclusiveCoverage");
        _tagNameMap.put(TAG_GETTYIMAGES_IMAGERANK, "ImageRank");
        _tagNameMap.put(TAG_GETTYIMAGES_ORIGINALCREATEDATETIME, "OriginalCreateDateTime");
        _tagNameMap.put(TAG_GETTYIMAGES_PARENTMEDIAEVENTID, "ParentMediaEventID");
        _tagNameMap.put(TAG_GETTYIMAGES_PERSONALITY, "Personality");
        _tagNameMap.put(TAG_GETTYIMAGES_ROUTINGDESTINATIONS, "RoutingDestinations");
        _tagNameMap.put(TAG_GETTYIMAGES_SECONDARYFTP, "SecondaryFTP");

        // GImage
        _tagNameMap.put(TAG_GIMAGE_IMAGEDATA, "ImageData");

        // GPano
        _tagNameMap.put(TAG_GPANO_CAPTURESOFTWARE, "CaptureSoftware");
        _tagNameMap.put(TAG_GPANO_CROPPEDAREAIMAGEWIDTHPIXELS, "CroppedAreaImageWidthPixels");
        _tagNameMap.put(TAG_GPANO_CROPPEDAREATOPPIXELS, "CroppedAreaTopPixels");
        _tagNameMap.put(TAG_GPANO_FIRSTPHOTODATE, "FirstPhotoDate");
        _tagNameMap.put(TAG_GPANO_FULLPANOWIDTHPIXELS, "FullPanoWidthPixels");
        _tagNameMap.put(TAG_GPANO_INITIALHORIZONTALFOVDEGREES, "InitialHorizontalFOVDegrees");
        _tagNameMap.put(TAG_GPANO_INITIALVIEWPITCHDEGREES, "InitialViewPitchDegrees");
        _tagNameMap.put(TAG_GPANO_LARGESTVALIDINTERIORRECTHEIGHT, "LargestValidInteriorRectHeight");
        _tagNameMap.put(TAG_GPANO_LARGESTVALIDINTERIORRECTTOP, "LargestValidInteriorRectTop");
        _tagNameMap.put(TAG_GPANO_LASTPHOTODATE, "LastPhotoDate");
        _tagNameMap.put(TAG_GPANO_POSEPITCHDEGREES, "PosePitchDegrees");
        _tagNameMap.put(TAG_GPANO_PROJECTIONTYPE, "ProjectionType");
        _tagNameMap.put(TAG_GPANO_STITCHINGSOFTWARE, "StitchingSoftware");

        // GSpherical
        _tagNameMap.put(TAG_GSPHERICAL_CROPPEDAREAIMAGEHEIGHTPIXELS, "CroppedAreaImageHeightPixels");
        _tagNameMap.put(TAG_GSPHERICAL_CROPPEDAREALEFTPIXELS, "CroppedAreaLeftPixels");
        _tagNameMap.put(TAG_GSPHERICAL_FULLPANOHEIGHTPIXELS, "FullPanoHeightPixels");
        _tagNameMap.put(TAG_GSPHERICAL_INITIALVIEWHEADINGDEGREES, "InitialViewHeadingDegrees");
        _tagNameMap.put(TAG_GSPHERICAL_INITIALVIEWROLLDEGREES, "InitialViewRollDegrees");
        _tagNameMap.put(TAG_GSPHERICAL_SOURCECOUNT, "SourceCount");
        _tagNameMap.put(TAG_GSPHERICAL_STEREOMODE, "StereoMode");
        _tagNameMap.put(TAG_GSPHERICAL_STITCHINGSOFTWARE, "StitchingSoftware");

        // ics
        _tagNameMap.put(TAG_ICS_APPVERSION, "AppVersion");
        _tagNameMap.put(TAG_ICS_SUBVERSIONS, "SubVersions");
        _tagNameMap.put(TAG_ICS_SUBVERSIONREFERENCE, "SubVersionReference");
        _tagNameMap.put(TAG_ICS_LABELNAME1, "LabelName1");
        _tagNameMap.put(TAG_ICS_REFERENCE1, "Reference1");
        _tagNameMap.put(TAG_ICS_LABELNAME2, "LabelName2");
        _tagNameMap.put(TAG_ICS_REFERENCE2, "Reference2");
        _tagNameMap.put(TAG_ICS_LABELNAME3, "LabelName3");
        _tagNameMap.put(TAG_ICS_REFERENCE3, "Reference3");
        _tagNameMap.put(TAG_ICS_LABELNAME4, "LabelName4");
        _tagNameMap.put(TAG_ICS_REFERENCE4, "Reference4");
        _tagNameMap.put(TAG_ICS_LABELNAME5, "LabelName5");
        _tagNameMap.put(TAG_ICS_REFERENCE5, "Reference5");
        _tagNameMap.put(TAG_ICS_LABELNAME6, "LabelName6");
        _tagNameMap.put(TAG_ICS_REFERENCE6, "Reference6");

        // iptcCore
        _tagNameMap.put(TAG_IPTCCORE_COUNTRYCODE, "CountryCode");
        _tagNameMap.put(TAG_IPTCCORE_CREATORCITY, "CreatorCity");
        _tagNameMap.put(TAG_IPTCCORE_CREATORADDRESS, "CreatorAddress");
        _tagNameMap.put(TAG_IPTCCORE_CREATORREGION, "CreatorRegion");
        _tagNameMap.put(TAG_IPTCCORE_CREATORWORKTELEPHONE, "CreatorWorkTelephone");
        _tagNameMap.put(TAG_IPTCCORE_INTELLECTUALGENRE, "IntellectualGenre");
        _tagNameMap.put(TAG_IPTCCORE_SCENE, "Scene");

        // iptcExt
        _tagNameMap.put(TAG_IPTCEXT_ABOUTCVTERM, "AboutCvTerm");
        _tagNameMap.put(TAG_IPTCEXT_ABOUTCVTERMID, "AboutCvTermId");
        _tagNameMap.put(TAG_IPTCEXT_ABOUTCVTERMREFINEDABOUT, "AboutCvTermRefinedAbout");
        _tagNameMap.put(TAG_IPTCEXT_ARTWORKOROBJECT, "ArtworkOrObject");
        _tagNameMap.put(TAG_IPTCEXT_ARTWORKCONTENTDESCRIPTION, "ArtworkContentDescription");
        _tagNameMap.put(TAG_IPTCEXT_ARTWORKCOPYRIGHTNOTICE, "ArtworkCopyrightNotice");
        _tagNameMap.put(TAG_IPTCEXT_ARTWORKCREATORID, "ArtworkCreatorID");
        _tagNameMap.put(TAG_IPTCEXT_ARTWORKCOPYRIGHTOWNERNAME, "ArtworkCopyrightOwnerName");
        _tagNameMap.put(TAG_IPTCEXT_ARTWORKLICENSORNAME, "ArtworkLicensorName");
        _tagNameMap.put(TAG_IPTCEXT_ARTWORKPHYSICALDESCRIPTION, "ArtworkPhysicalDescription");
        _tagNameMap.put(TAG_IPTCEXT_ARTWORKSOURCEINVENTORYNO, "ArtworkSourceInventoryNo");
        _tagNameMap.put(TAG_IPTCEXT_ARTWORKSTYLEPERIOD, "ArtworkStylePeriod");
        _tagNameMap.put(TAG_IPTCEXT_AUDIOBITRATE, "AudioBitrate");
        _tagNameMap.put(TAG_IPTCEXT_AUDIOCHANNELCOUNT, "AudioChannelCount");
        _tagNameMap.put(TAG_IPTCEXT_CONTAINERFORMAT, "ContainerFormat");
        _tagNameMap.put(TAG_IPTCEXT_CONTAINERFORMATNAME, "ContainerFormatName");
        _tagNameMap.put(TAG_IPTCEXT_CONTRIBUTORIDENTIFIER, "ContributorIdentifier");
        _tagNameMap.put(TAG_IPTCEXT_CONTRIBUTORROLE, "ContributorRole");
        _tagNameMap.put(TAG_IPTCEXT_CREATOR, "Creator");
        _tagNameMap.put(TAG_IPTCEXT_CREATORNAME, "CreatorName");
        _tagNameMap.put(TAG_IPTCEXT_CONTROLLEDVOCABULARYTERM, "ControlledVocabularyTerm");
        _tagNameMap.put(TAG_IPTCEXT_DATAONSCREENREGION, "DataOnScreenRegion");
        _tagNameMap.put(TAG_IPTCEXT_DATAONSCREENREGIONH, "DataOnScreenRegionH");
        _tagNameMap.put(TAG_IPTCEXT_DATAONSCREENREGIONUNIT, "DataOnScreenRegionUnit");
        _tagNameMap.put(TAG_IPTCEXT_DATAONSCREENREGIONX, "DataOnScreenRegionX");
        _tagNameMap.put(TAG_IPTCEXT_DIGITALIMAGEGUID, "DigitalImageGUID");
        _tagNameMap.put(TAG_IPTCEXT_DIGITALSOURCETYPE, "DigitalSourceType");
        _tagNameMap.put(TAG_IPTCEXT_DOPESHEETLINK, "DopesheetLink");
        _tagNameMap.put(TAG_IPTCEXT_DOPESHEETLINKLINKQUALIFIER, "DopesheetLinkLinkQualifier");
        _tagNameMap.put(TAG_IPTCEXT_EMBEDDEDENCODEDRIGHTSEXPR, "EmbeddedEncodedRightsExpr");
        _tagNameMap.put(TAG_IPTCEXT_EMBEDDEDENCODEDRIGHTSEXPRLANGID, "EmbeddedEncodedRightsExprLangID");
        _tagNameMap.put(TAG_IPTCEXT_EPISODEIDENTIFIER, "EpisodeIdentifier");
        _tagNameMap.put(TAG_IPTCEXT_EPISODENUMBER, "EpisodeNumber");
        _tagNameMap.put(TAG_IPTCEXT_SHOWNEVENT, "ShownEvent");
        _tagNameMap.put(TAG_IPTCEXT_SHOWNEVENTNAME, "ShownEventName");
        _tagNameMap.put(TAG_IPTCEXT_FEEDIDENTIFIER, "FeedIdentifier");
        _tagNameMap.put(TAG_IPTCEXT_GENRECVID, "GenreCvId");
        _tagNameMap.put(TAG_IPTCEXT_GENRECVTERMNAME, "GenreCvTermName");
        _tagNameMap.put(TAG_IPTCEXT_HEADLINE, "Headline");
        _tagNameMap.put(TAG_IPTCEXT_LINKEDENCRIGHTSEXPR, "LinkedEncRightsExpr");
        _tagNameMap.put(TAG_IPTCEXT_LINKEDENCODEDRIGHTSEXPRTYPE, "LinkedEncodedRightsExprType");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONCREATED, "LocationCreated");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONCREATEDCOUNTRYCODE, "LocationCreatedCountryCode");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONCREATEDGPSALTITUDE, "LocationCreatedGPSAltitude");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONCREATEDGPSLONGITUDE, "LocationCreatedGPSLongitude");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONCREATEDLOCATIONID, "LocationCreatedLocationId");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONCREATEDPROVINCESTATE, "LocationCreatedProvinceState");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONCREATEDWORLDREGION, "LocationCreatedWorldRegion");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONSHOWNCITY, "LocationShownCity");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONSHOWNCOUNTRYNAME, "LocationShownCountryName");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONSHOWNGPSLATITUDE, "LocationShownGPSLatitude");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONSHOWNIDENTIFIER, "LocationShownIdentifier");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONSHOWNLOCATIONNAME, "LocationShownLocationName");
        _tagNameMap.put(TAG_IPTCEXT_LOCATIONSHOWNSUBLOCATION, "LocationShownSublocation");
        _tagNameMap.put(TAG_IPTCEXT_MAXAVAILHEIGHT, "MaxAvailHeight");
        _tagNameMap.put(TAG_IPTCEXT_MODELAGE, "ModelAge");
        _tagNameMap.put(TAG_IPTCEXT_ORGANISATIONINIMAGENAME, "OrganisationInImageName");
        _tagNameMap.put(TAG_IPTCEXT_PERSONHEARDIDENTIFIER, "PersonHeardIdentifier");
        _tagNameMap.put(TAG_IPTCEXT_PERSONINIMAGE, "PersonInImage");
        _tagNameMap.put(TAG_IPTCEXT_PERSONINIMAGECHARACTERISTIC, "PersonInImageCharacteristic");
        _tagNameMap.put(TAG_IPTCEXT_PERSONINIMAGECVTERMID, "PersonInImageCvTermId");
        _tagNameMap.put(TAG_IPTCEXT_PERSONINIMAGECVTERMREFINEDABOUT, "PersonInImageCvTermRefinedAbout");
        _tagNameMap.put(TAG_IPTCEXT_PERSONINIMAGEID, "PersonInImageId");
        _tagNameMap.put(TAG_IPTCEXT_PRODUCTINIMAGE, "ProductInImage");
        _tagNameMap.put(TAG_IPTCEXT_PRODUCTINIMAGEGTIN, "ProductInImageGTIN");
        _tagNameMap.put(TAG_IPTCEXT_PUBLICATIONEVENT, "PublicationEvent");
        _tagNameMap.put(TAG_IPTCEXT_PUBLICATIONEVENTIDENTIFIER, "PublicationEventIdentifier");
        _tagNameMap.put(TAG_IPTCEXT_RATING, "Rating");
        _tagNameMap.put(TAG_IPTCEXT_RATINGREGIONCITY, "RatingRegionCity");
        _tagNameMap.put(TAG_IPTCEXT_RATINGREGIONCOUNTRYNAME, "RatingRegionCountryName");
        _tagNameMap.put(TAG_IPTCEXT_RATINGREGIONGPSLATITUDE, "RatingRegionGPSLatitude");
        _tagNameMap.put(TAG_IPTCEXT_RATINGREGIONIDENTIFIER, "RatingRegionIdentifier");
        _tagNameMap.put(TAG_IPTCEXT_RATINGREGIONLOCATIONNAME, "RatingRegionLocationName");
        _tagNameMap.put(TAG_IPTCEXT_RATINGREGIONSUBLOCATION, "RatingRegionSublocation");
        _tagNameMap.put(TAG_IPTCEXT_RATINGSCALEMAXVALUE, "RatingScaleMaxValue");
        _tagNameMap.put(TAG_IPTCEXT_RATINGSOURCELINK, "RatingSourceLink");
        _tagNameMap.put(TAG_IPTCEXT_RATINGVALUELOGOLINK, "RatingValueLogoLink");
        _tagNameMap.put(TAG_IPTCEXT_REGISTRYENTRYROLE, "RegistryEntryRole");
        _tagNameMap.put(TAG_IPTCEXT_REGISTRYORGANISATIONID, "RegistryOrganisationID");
        _tagNameMap.put(TAG_IPTCEXT_SEASON, "Season");
        _tagNameMap.put(TAG_IPTCEXT_SEASONNAME, "SeasonName");
        _tagNameMap.put(TAG_IPTCEXT_SERIES, "Series");
        _tagNameMap.put(TAG_IPTCEXT_SERIESNAME, "SeriesName");
        _tagNameMap.put(TAG_IPTCEXT_STREAMREADY, "StreamReady");
        _tagNameMap.put(TAG_IPTCEXT_SUPPLYCHAINSOURCE, "SupplyChainSource");
        _tagNameMap.put(TAG_IPTCEXT_SUPPLYCHAINSOURCENAME, "SupplyChainSourceName");
        _tagNameMap.put(TAG_IPTCEXT_TEMPORALCOVERAGEFROM, "TemporalCoverageFrom");
        _tagNameMap.put(TAG_IPTCEXT_TRANSCRIPT, "Transcript");
        _tagNameMap.put(TAG_IPTCEXT_TRANSCRIPTLINKLINK, "TranscriptLinkLink");
        _tagNameMap.put(TAG_IPTCEXT_VIDEOBITRATE, "VideoBitrate");
        _tagNameMap.put(TAG_IPTCEXT_VIDEODISPLAYASPECTRATIO, "VideoDisplayAspectRatio");
        _tagNameMap.put(TAG_IPTCEXT_VIDEOSHOTTYPE, "VideoShotType");
        _tagNameMap.put(TAG_IPTCEXT_VIDEOSHOTTYPENAME, "VideoShotTypeName");
        _tagNameMap.put(TAG_IPTCEXT_VISUALCOLOR, "VisualColor");
        _tagNameMap.put(TAG_IPTCEXT_WORKFLOWTAGCVID, "WorkflowTagCvId");
        _tagNameMap.put(TAG_IPTCEXT_WORKFLOWTAGCVTERMNAME, "WorkflowTagCvTermName");

        // Lightroom
        _tagNameMap.put(TAG_LIGHTROOM_HIERARCHICALSUBJECT, "HierarchicalSubject");

        // MediaPro
        _tagNameMap.put(TAG_MEDIAPRO_CATALOGSETS, "CatalogSets");
        _tagNameMap.put(TAG_MEDIAPRO_LOCATION, "Location");
        _tagNameMap.put(TAG_MEDIAPRO_STATUS, "Status");

        // pdf
        _tagNameMap.put(TAG_PDF_AUTHOR, "Author");
        _tagNameMap.put(TAG_PDF_CREATIONDATE, "CreationDate");
        _tagNameMap.put(TAG_PDF_KEYWORDS, "Keywords");
        _tagNameMap.put(TAG_PDF_MODDATE, "ModDate");
        _tagNameMap.put(TAG_PDF_PRODUCER, "Producer");
        _tagNameMap.put(TAG_PDF_TITLE, "Title");

        // pdfx

        // photoshop
        _tagNameMap.put(TAG_PHOTOSHOP_AUTHORSPOSITION, "AuthorsPosition");
        _tagNameMap.put(TAG_PHOTOSHOP_CATEGORY, "Category");
        _tagNameMap.put(TAG_PHOTOSHOP_COLORMODE, "ColorMode");
        _tagNameMap.put(TAG_PHOTOSHOP_CREDIT, "Credit");
        _tagNameMap.put(TAG_PHOTOSHOP_DOCUMENTANCESTORS, "DocumentAncestors");
        _tagNameMap.put(TAG_PHOTOSHOP_HEADLINE, "Headline");
        _tagNameMap.put(TAG_PHOTOSHOP_ICCPROFILENAME, "ICCProfileName");
        _tagNameMap.put(TAG_PHOTOSHOP_LEGACYIPTCDIGEST, "LegacyIPTCDigest");
        _tagNameMap.put(TAG_PHOTOSHOP_SOURCE, "Source");
        _tagNameMap.put(TAG_PHOTOSHOP_SUPPLEMENTALCATEGORIES, "SupplementalCategories");
        _tagNameMap.put(TAG_PHOTOSHOP_TEXTLAYERNAME, "TextLayerName");
        _tagNameMap.put(TAG_PHOTOSHOP_TRANSMISSIONREFERENCE, "TransmissionReference");

        // PixelLive
        _tagNameMap.put(TAG_PIXELLIVE_AUTHOR, "Author");
        _tagNameMap.put(TAG_PIXELLIVE_COPYRIGHT, "Copyright");
        _tagNameMap.put(TAG_PIXELLIVE_GENRE, "Genre");

        // pmi
        _tagNameMap.put(TAG_PMI_COLOR, "Color");
        _tagNameMap.put(TAG_PMI_DISPLAYNAME, "DisplayName");
        _tagNameMap.put(TAG_PMI_EVENTALIAS, "EventAlias");
        _tagNameMap.put(TAG_PMI_EVENTSTART, "EventStart");
        _tagNameMap.put(TAG_PMI_EVENTTYPE, "EventType");
        _tagNameMap.put(TAG_PMI_FRAMING, "Framing");
        _tagNameMap.put(TAG_PMI_MAKE, "Make");
        _tagNameMap.put(TAG_PMI_MODEL, "Model");
        _tagNameMap.put(TAG_PMI_OBJECTDESCRIPTION, "ObjectDescription");
        _tagNameMap.put(TAG_PMI_OBJECTTYPE, "ObjectType");
        _tagNameMap.put(TAG_PMI_POSITIONDESCRIPTOR, "PositionDescriptor");
        _tagNameMap.put(TAG_PMI_PRODUCTIDTYPE, "ProductIDType");
        _tagNameMap.put(TAG_PMI_SEQUENCENAME, "SequenceName");
        _tagNameMap.put(TAG_PMI_SEQUENCETOTALNUMBER, "SequenceTotalNumber");
        _tagNameMap.put(TAG_PMI_SHOOTID, "ShootID");
        _tagNameMap.put(TAG_PMI_SLIDESHOWNUMBER, "SlideshowNumber");
        _tagNameMap.put(TAG_PMI_VIEWPOINT, "Viewpoint");

        // prism
        _tagNameMap.put(TAG_PRISM_ACEDEMICFIELD, "AcedemicField");
        _tagNameMap.put(TAG_PRISM_AGGREGATIONTYPE, "AggregationType");
        _tagNameMap.put(TAG_PRISM_ALTERNATETITLEA_LANG, "AlternateTitleA-lang");
        _tagNameMap.put(TAG_PRISM_ALTERNATETITLETEXT, "AlternateTitleText");
        _tagNameMap.put(TAG_PRISM_BLOGURL, "BlogURL");
        _tagNameMap.put(TAG_PRISM_BYTECOUNT, "ByteCount");
        _tagNameMap.put(TAG_PRISM_CHANNELA_LANG, "ChannelA-lang");
        _tagNameMap.put(TAG_PRISM_CHANNELSUBCHANNEL1, "ChannelSubchannel1");
        _tagNameMap.put(TAG_PRISM_CHANNELSUBCHANNEL3, "ChannelSubchannel3");
        _tagNameMap.put(TAG_PRISM_COMPLIANCEPROFILE, "ComplianceProfile");
        _tagNameMap.put(TAG_PRISM_COPYRIGHTYEAR, "CopyrightYear");
        _tagNameMap.put(TAG_PRISM_COVERDATE, "CoverDate");
        _tagNameMap.put(TAG_PRISM_CREATIONDATE, "CreationDate");
        _tagNameMap.put(TAG_PRISM_DEVICE, "Device");
        _tagNameMap.put(TAG_PRISM_DOI, "DOI");
        _tagNameMap.put(TAG_PRISM_EISSN, "EIssn");
        _tagNameMap.put(TAG_PRISM_EVENT, "Event");
        _tagNameMap.put(TAG_PRISM_HASALTERNATIVE, "HasAlternative");
        _tagNameMap.put(TAG_PRISM_HASCORRECTIONA_LANG, "HasCorrectionA-lang");
        _tagNameMap.put(TAG_PRISM_HASCORRECTIONTEXT, "HasCorrectionText");
        _tagNameMap.put(TAG_PRISM_INDUSTRY, "Industry");
        _tagNameMap.put(TAG_PRISM_ISBN, "ISBN");
        _tagNameMap.put(TAG_PRISM_ISSN, "ISSN");
        _tagNameMap.put(TAG_PRISM_ISSUENAME, "IssueName");
        _tagNameMap.put(TAG_PRISM_ISSUETYPE, "IssueType");
        _tagNameMap.put(TAG_PRISM_KEYWORD, "Keyword");
        _tagNameMap.put(TAG_PRISM_KILLDATEA_PLATFORM, "KillDateA-platform");
        _tagNameMap.put(TAG_PRISM_LINK, "Link");
        _tagNameMap.put(TAG_PRISM_MODIFICATIONDATE, "ModificationDate");
        _tagNameMap.put(TAG_PRISM_NUMBER, "Number");
        _tagNameMap.put(TAG_PRISM_OFFSALEDATE, "OffSaleDate");
        _tagNameMap.put(TAG_PRISM_OFFSALEDATEDATE, "OffSaleDateDate");
        _tagNameMap.put(TAG_PRISM_ONSALEDATEA_PLATFORM, "OnSaleDateA-platform");
        _tagNameMap.put(TAG_PRISM_ONSALEDAY, "OnSaleDay");
        _tagNameMap.put(TAG_PRISM_ONSALEDAYDAY, "OnSaleDayDay");
        _tagNameMap.put(TAG_PRISM_ORIGINPLATFORM, "OriginPlatform");
        _tagNameMap.put(TAG_PRISM_PAGEPROGRESSIONDIRECTION, "PageProgressionDirection");
        _tagNameMap.put(TAG_PRISM_PERSON, "Person");
        _tagNameMap.put(TAG_PRISM_PRODUCTCODE, "ProductCode");
        _tagNameMap.put(TAG_PRISM_PUBLICATIONDATE, "PublicationDate");
        _tagNameMap.put(TAG_PRISM_PUBLICATIONDATEDATE, "PublicationDateDate");
        _tagNameMap.put(TAG_PRISM_PUBLICATIONDISPLAYDATEA_PLATFORM, "PublicationDisplayDateA-platform");
        _tagNameMap.put(TAG_PRISM_PUBLICATIONNAME, "PublicationName");
        _tagNameMap.put(TAG_PRISM_RATING, "Rating");
        _tagNameMap.put(TAG_PRISM_SECTION, "Section");
        _tagNameMap.put(TAG_PRISM_SERIESNUMBER, "SeriesNumber");
        _tagNameMap.put(TAG_PRISM_SPORT, "Sport");
        _tagNameMap.put(TAG_PRISM_SUBSECTION1, "Subsection1");
        _tagNameMap.put(TAG_PRISM_SUBSECTION3, "Subsection3");
        _tagNameMap.put(TAG_PRISM_SUBTITLE, "Subtitle");
        _tagNameMap.put(TAG_PRISM_SUPPLEMENTSTARTINGPAGE, "SupplementStartingPage");
        _tagNameMap.put(TAG_PRISM_TEASER, "Teaser");
        _tagNameMap.put(TAG_PRISM_TIMEPERIOD, "TimePeriod");
        _tagNameMap.put(TAG_PRISM_URLA_PLATFORM, "URLA-platform");
        _tagNameMap.put(TAG_PRISM_USPSNUMBER, "UspsNumber");
        _tagNameMap.put(TAG_PRISM_VOLUME, "Volume");

        // prl
        _tagNameMap.put(TAG_PRL_GEOGRAPHY, "Geography");
        _tagNameMap.put(TAG_PRL_USAGE, "Usage");

        // prm
        _tagNameMap.put(TAG_PRM_COOKINGEQUIPMENT, "CookingEquipment");
        _tagNameMap.put(TAG_PRM_COURSE, "Course");
        _tagNameMap.put(TAG_PRM_DIETARYNEEDS, "DietaryNeeds");
        _tagNameMap.put(TAG_PRM_DURATION, "Duration");
        _tagNameMap.put(TAG_PRM_MAININGREDIENT, "MainIngredient");
        _tagNameMap.put(TAG_PRM_RECIPEENDINGPAGE, "RecipeEndingPage");
        _tagNameMap.put(TAG_PRM_RECIPESOURCE, "RecipeSource");
        _tagNameMap.put(TAG_PRM_RECIPETITLE, "RecipeTitle");
        _tagNameMap.put(TAG_PRM_SKILLLEVEL, "SkillLevel");
        _tagNameMap.put(TAG_PRM_YIELD, "Yield");

        // pur
        _tagNameMap.put(TAG_PUR_ADULTCONTENTWARNING, "AdultContentWarning");
        _tagNameMap.put(TAG_PUR_COPYRIGHT, "Copyright");
        _tagNameMap.put(TAG_PUR_EMBARGODATE, "EmbargoDate");
        _tagNameMap.put(TAG_PUR_EXPIRATIONDATE, "ExpirationDate");
        _tagNameMap.put(TAG_PUR_OPTIONENDDATE, "OptionEndDate");
        _tagNameMap.put(TAG_PUR_RESTRICTIONS, "Restrictions");
        _tagNameMap.put(TAG_PUR_RIGHTSAGENT, "RightsAgent");

        // rdf
        _tagNameMap.put(TAG_RDF_ABOUT, "About");

        // swf
        _tagNameMap.put(TAG_SWF_BACKGROUNDALPHA, "BackgroundAlpha");
        _tagNameMap.put(TAG_SWF_MAXSTORAGE, "MaxStorage");

        // tiff
        _tagNameMap.put(TAG_TIFF_ARTIST, "Artist");
        _tagNameMap.put(TAG_TIFF_COMPRESSION, "Compression");
        _tagNameMap.put(TAG_TIFF_DATETIME, "DateTime");
        _tagNameMap.put(TAG_TIFF_IMAGEHEIGHT, "ImageHeight");
        _tagNameMap.put(TAG_TIFF_MAKE, "Make");
        _tagNameMap.put(TAG_TIFF_NATIVEDIGEST, "NativeDigest");
        _tagNameMap.put(TAG_TIFF_PHOTOMETRICINTERPRETATION, "PhotometricInterpretation");
        _tagNameMap.put(TAG_TIFF_PRIMARYCHROMATICITIES, "PrimaryChromaticities");
        _tagNameMap.put(TAG_TIFF_RESOLUTIONUNIT, "ResolutionUnit");
        _tagNameMap.put(TAG_TIFF_SOFTWARE, "Software");
        _tagNameMap.put(TAG_TIFF_WHITEPOINT, "WhitePoint");
        _tagNameMap.put(TAG_TIFF_YCBCRCOEFFICIENTS, "YCbCrCoefficients");
        _tagNameMap.put(TAG_TIFF_YCBCRSUBSAMPLING, "YCbCrSubSampling");

        // x
        _tagNameMap.put(TAG_X_XMPTOOLKIT, "XMPToolkit");

        // xmp
        _tagNameMap.put(TAG_XMP_ADVISORY, "Advisory");
        _tagNameMap.put(TAG_XMP_BASEURL, "BaseURL");
        _tagNameMap.put(TAG_XMP_CREATORTOOL, "CreatorTool");
        _tagNameMap.put(TAG_XMP_FORMAT, "Format");
        _tagNameMap.put(TAG_XMP_KEYWORDS, "Keywords");
        _tagNameMap.put(TAG_XMP_METADATADATE, "MetadataDate");
        _tagNameMap.put(TAG_XMP_NICKNAME, "Nickname");
        _tagNameMap.put(TAG_XMP_PAGEIMAGEFORMAT, "PageImageFormat");
        _tagNameMap.put(TAG_XMP_PAGEIMAGE, "PageImage");
        _tagNameMap.put(TAG_XMP_PAGEIMAGEWIDTH, "PageImageWidth");
        _tagNameMap.put(TAG_XMP_THUMBNAILS, "Thumbnails");
        _tagNameMap.put(TAG_XMP_THUMBNAILHEIGHT, "ThumbnailHeight");
        _tagNameMap.put(TAG_XMP_THUMBNAILWIDTH, "ThumbnailWidth");

        // xmpBJ
        _tagNameMap.put(TAG_XMPBJ_JOBREF, "JobRef");
        _tagNameMap.put(TAG_XMPBJ_JOBREFNAME, "JobRefName");

        // xmpDM
        _tagNameMap.put(TAG_XMPDM_ABSPEAKAUDIOFILEPATH, "AbsPeakAudioFilePath");
        _tagNameMap.put(TAG_XMPDM_ALTTAPENAME, "AltTapeName");
        _tagNameMap.put(TAG_XMPDM_ALTTIMECODETIMEFORMAT, "AltTimecodeTimeFormat");
        _tagNameMap.put(TAG_XMPDM_ALTTIMECODEVALUE, "AltTimecodeValue");
        _tagNameMap.put(TAG_XMPDM_AUDIOCHANNELTYPE, "AudioChannelType");
        _tagNameMap.put(TAG_XMPDM_AUDIOMODDATE, "AudioModDate");
        _tagNameMap.put(TAG_XMPDM_AUDIOSAMPLETYPE, "AudioSampleType");
        _tagNameMap.put(TAG_XMPDM_BEATSPLICEPARAMSRISEINDECIBEL, "BeatSpliceParamsRiseInDecibel");
        _tagNameMap.put(TAG_XMPDM_BEATSPLICEPARAMSRISEINTIMEDURATIONSCALE, "BeatSpliceParamsRiseInTimeDurationScale");
        _tagNameMap.put(TAG_XMPDM_BEATSPLICEPARAMSUSEFILEBEATSMARKER, "BeatSpliceParamsUseFileBeatsMarker");
        _tagNameMap.put(TAG_XMPDM_CAMERALABEL, "CameraLabel");
        _tagNameMap.put(TAG_XMPDM_CAMERAMOVE, "CameraMove");
        _tagNameMap.put(TAG_XMPDM_DMCOMMENT, "DMComment");
        _tagNameMap.put(TAG_XMPDM_CONTRIBUTEDMEDIA, "ContributedMedia");
        _tagNameMap.put(TAG_XMPDM_CONTRIBUTEDMEDIADURATIONSCALE, "ContributedMediaDurationScale");
        _tagNameMap.put(TAG_XMPDM_CONTRIBUTEDMEDIAMANAGED, "ContributedMediaManaged");
        _tagNameMap.put(TAG_XMPDM_CONTRIBUTEDMEDIASTARTTIME, "ContributedMediaStartTime");
        _tagNameMap.put(TAG_XMPDM_CONTRIBUTEDMEDIASTARTTIMEVALUE, "ContributedMediaStartTimeValue");
        _tagNameMap.put(TAG_XMPDM_CONTRIBUTEDMEDIAWEBSTATEMENT, "ContributedMediaWebStatement");
        _tagNameMap.put(TAG_XMPDM_DIRECTOR, "Director");
        _tagNameMap.put(TAG_XMPDM_DISCNUMBER, "DiscNumber");
        _tagNameMap.put(TAG_XMPDM_DURATIONSCALE, "DurationScale");
        _tagNameMap.put(TAG_XMPDM_ENGINEER, "Engineer");
        _tagNameMap.put(TAG_XMPDM_GENRE, "Genre");
        _tagNameMap.put(TAG_XMPDM_INSTRUMENT, "Instrument");
        _tagNameMap.put(TAG_XMPDM_INTROTIMESCALE, "IntroTimeScale");
        _tagNameMap.put(TAG_XMPDM_KEY, "Key");
        _tagNameMap.put(TAG_XMPDM_LOOP, "Loop");
        _tagNameMap.put(TAG_XMPDM_MARKERS, "Markers");
        _tagNameMap.put(TAG_XMPDM_MARKERSCUEPOINTPARAMS, "MarkersCuePointParams");
        _tagNameMap.put(TAG_XMPDM_MARKERSCUEPOINTPARAMSVALUE, "MarkersCuePointParamsValue");
        _tagNameMap.put(TAG_XMPDM_MARKERSDURATION, "MarkersDuration");
        _tagNameMap.put(TAG_XMPDM_MARKERSNAME, "MarkersName");
        _tagNameMap.put(TAG_XMPDM_MARKERSSPEAKER, "MarkersSpeaker");
        _tagNameMap.put(TAG_XMPDM_MARKERSTARGET, "MarkersTarget");
        _tagNameMap.put(TAG_XMPDM_METADATAMODDATE, "MetadataModDate");
        _tagNameMap.put(TAG_XMPDM_OUTCUE, "OutCue");
        _tagNameMap.put(TAG_XMPDM_OUTCUEVALUE, "OutCueValue");
        _tagNameMap.put(TAG_XMPDM_PROJECTNAME, "ProjectName");
        _tagNameMap.put(TAG_XMPDM_PROJECTREFPATH, "ProjectRefPath");
        _tagNameMap.put(TAG_XMPDM_PULLDOWN, "PullDown");
        _tagNameMap.put(TAG_XMPDM_RELATIVETIMESTAMP, "RelativeTimestamp");
        _tagNameMap.put(TAG_XMPDM_RELATIVETIMESTAMPVALUE, "RelativeTimestampValue");
        _tagNameMap.put(TAG_XMPDM_RESAMPLEPARAMS, "ResampleParams");
        _tagNameMap.put(TAG_XMPDM_SCALETYPE, "ScaleType");
        _tagNameMap.put(TAG_XMPDM_SHOTDATE, "ShotDate");
        _tagNameMap.put(TAG_XMPDM_SHOTLOCATION, "ShotLocation");
        _tagNameMap.put(TAG_XMPDM_SHOTNUMBER, "ShotNumber");
        _tagNameMap.put(TAG_XMPDM_SPEAKERPLACEMENT, "SpeakerPlacement");
        _tagNameMap.put(TAG_XMPDM_STARTTIMECODETIMEFORMAT, "StartTimecodeTimeFormat");
        _tagNameMap.put(TAG_XMPDM_STARTTIMECODEVALUE, "StartTimecodeValue");
        _tagNameMap.put(TAG_XMPDM_STARTTIMESCALE, "StartTimeScale");
        _tagNameMap.put(TAG_XMPDM_TAKENUMBER, "TakeNumber");
        _tagNameMap.put(TAG_XMPDM_TEMPO, "Tempo");
        _tagNameMap.put(TAG_XMPDM_TIMESCALEPARAMSFRAMEOVERLAPPINGPERCENTAGE, "TimeScaleParamsFrameOverlappingPercentage");
        _tagNameMap.put(TAG_XMPDM_TIMESCALEPARAMSQUALITY, "TimeScaleParamsQuality");
        _tagNameMap.put(TAG_XMPDM_TRACKNUMBER, "TrackNumber");
        _tagNameMap.put(TAG_XMPDM_TRACKSFRAMERATE, "TracksFrameRate");
        _tagNameMap.put(TAG_XMPDM_TRACKSMARKERSCOMMENT, "TracksMarkersComment");
        _tagNameMap.put(TAG_XMPDM_TRACKSMARKERSCUEPOINTPARAMSKEY, "TracksMarkersCuePointParamsKey");
        _tagNameMap.put(TAG_XMPDM_TRACKSMARKERSCUEPOINTTYPE, "TracksMarkersCuePointType");
        _tagNameMap.put(TAG_XMPDM_TRACKSMARKERSLOCATION, "TracksMarkersLocation");
        _tagNameMap.put(TAG_XMPDM_TRACKSMARKERSPROBABILITY, "TracksMarkersProbability");
        _tagNameMap.put(TAG_XMPDM_TRACKSMARKERSSTARTTIME, "TracksMarkersStartTime");
        _tagNameMap.put(TAG_XMPDM_TRACKSMARKERSTYPE, "TracksMarkersType");
        _tagNameMap.put(TAG_XMPDM_TRACKSTRACKTYPE, "TracksTrackType");
        _tagNameMap.put(TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLOR, "VideoAlphaPremultipleColor");
        _tagNameMap.put(TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORB, "VideoAlphaPremultipleColorB");
        _tagNameMap.put(TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORBLUE, "VideoAlphaPremultipleColorBlue");
        _tagNameMap.put(TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORGRAY, "VideoAlphaPremultipleColorGray");
        _tagNameMap.put(TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORL, "VideoAlphaPremultipleColorL");
        _tagNameMap.put(TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORMODE, "VideoAlphaPremultipleColorMode");
        _tagNameMap.put(TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORSWATCHNAME, "VideoAlphaPremultipleColorSwatchName");
        _tagNameMap.put(TAG_XMPDM_VIDEOALPHAPREMULTIPLECOLORTYPE, "VideoAlphaPremultipleColorType");
        _tagNameMap.put(TAG_XMPDM_VIDEOALPHAUNITYISTRANSPARENT, "VideoAlphaUnityIsTransparent");
        _tagNameMap.put(TAG_XMPDM_VIDEOCOMPRESSOR, "VideoCompressor");
        _tagNameMap.put(TAG_XMPDM_VIDEOFRAMERATE, "VideoFrameRate");
        _tagNameMap.put(TAG_XMPDM_VIDEOFRAMESIZEH, "VideoFrameSizeH");
        _tagNameMap.put(TAG_XMPDM_VIDEOFRAMESIZEW, "VideoFrameSizeW");
        _tagNameMap.put(TAG_XMPDM_VIDEOPIXELASPECTRATIO, "VideoPixelAspectRatio");

        // xmpMM
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROM, "DerivedFrom");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMDOCUMENTID, "DerivedFromDocumentID");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMFROMPART, "DerivedFromFromPart");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMLASTMODIFYDATE, "DerivedFromLastModifyDate");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMLINKCATEGORY, "DerivedFromLinkCategory");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMMANAGER, "DerivedFromManager");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMMANAGETO, "DerivedFromManageTo");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMMASKMARKERS, "DerivedFromMaskMarkers");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMPARTMAPPING, "DerivedFromPartMapping");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMPLACEDXRESOLUTION, "DerivedFromPlacedXResolution");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMRENDITIONCLASS, "DerivedFromRenditionClass");
        _tagNameMap.put(TAG_XMPMM_DERIVEDFROMTOPART, "DerivedFromToPart");
        _tagNameMap.put(TAG_XMPMM_DOCUMENTID, "DocumentID");
        _tagNameMap.put(TAG_XMPMM_HISTORYACTION, "HistoryAction");
        _tagNameMap.put(TAG_XMPMM_HISTORYINSTANCEID, "HistoryInstanceID");
        _tagNameMap.put(TAG_XMPMM_HISTORYSOFTWAREAGENT, "HistorySoftwareAgent");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTS, "Ingredients");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSDOCUMENTID, "IngredientsDocumentID");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSFROMPART, "IngredientsFromPart");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSLASTMODIFYDATE, "IngredientsLastModifyDate");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSLINKCATEGORY, "IngredientsLinkCategory");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSMANAGER, "IngredientsManager");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSMANAGETO, "IngredientsManageTo");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSMASKMARKERS, "IngredientsMaskMarkers");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSPARTMAPPING, "IngredientsPartMapping");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSPLACEDXRESOLUTION, "IngredientsPlacedXResolution");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSRENDITIONCLASS, "IngredientsRenditionClass");
        _tagNameMap.put(TAG_XMPMM_INGREDIENTSTOPART, "IngredientsToPart");
        _tagNameMap.put(TAG_XMPMM_INSTANCEID, "InstanceID");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROM, "ManagedFrom");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMDOCUMENTID, "ManagedFromDocumentID");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMFROMPART, "ManagedFromFromPart");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMLASTMODIFYDATE, "ManagedFromLastModifyDate");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMLINKCATEGORY, "ManagedFromLinkCategory");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMMANAGER, "ManagedFromManager");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMMANAGETO, "ManagedFromManageTo");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMMASKMARKERS, "ManagedFromMaskMarkers");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMPARTMAPPING, "ManagedFromPartMapping");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMPLACEDXRESOLUTION, "ManagedFromPlacedXResolution");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMRENDITIONCLASS, "ManagedFromRenditionClass");
        _tagNameMap.put(TAG_XMPMM_MANAGEDFROMTOPART, "ManagedFromToPart");
        _tagNameMap.put(TAG_XMPMM_MANAGER, "Manager");
        _tagNameMap.put(TAG_XMPMM_MANAGETO, "ManageTo");
        _tagNameMap.put(TAG_XMPMM_MANIFEST, "Manifest");
        _tagNameMap.put(TAG_XMPMM_MANIFESTPLACEDRESOLUTIONUNIT, "ManifestPlacedResolutionUnit");
        _tagNameMap.put(TAG_XMPMM_MANIFESTPLACEDYRESOLUTION, "ManifestPlacedYResolution");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCEALTERNATEPATHS, "ManifestReferenceAlternatePaths");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCEFILEPATH, "ManifestReferenceFilePath");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCEINSTANCEID, "ManifestReferenceInstanceID");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCELASTURL, "ManifestReferenceLastURL");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCELINKFORM, "ManifestReferenceLinkForm");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCEMANAGERVARIANT, "ManifestReferenceManagerVariant");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCEMANAGEUI, "ManifestReferenceManageUI");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCEORIGINALDOCUMENTID, "ManifestReferenceOriginalDocumentID");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCEPLACEDRESOLUTIONUNIT, "ManifestReferencePlacedResolutionUnit");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCEPLACEDYRESOLUTION, "ManifestReferencePlacedYResolution");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCERENDITIONPARAMS, "ManifestReferenceRenditionParams");
        _tagNameMap.put(TAG_XMPMM_MANIFESTREFERENCEVERSIONID, "ManifestReferenceVersionID");
        _tagNameMap.put(TAG_XMPMM_PANTRY, "Pantry");
        _tagNameMap.put(TAG_XMPMM_RENDITIONCLASS, "RenditionClass");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFALTERNATEPATHS, "RenditionOfAlternatePaths");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFFILEPATH, "RenditionOfFilePath");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFINSTANCEID, "RenditionOfInstanceID");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFLASTURL, "RenditionOfLastURL");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFLINKFORM, "RenditionOfLinkForm");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFMANAGERVARIANT, "RenditionOfManagerVariant");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFMANAGEUI, "RenditionOfManageUI");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFORIGINALDOCUMENTID, "RenditionOfOriginalDocumentID");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFPLACEDRESOLUTIONUNIT, "RenditionOfPlacedResolutionUnit");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFPLACEDYRESOLUTION, "RenditionOfPlacedYResolution");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFRENDITIONPARAMS, "RenditionOfRenditionParams");
        _tagNameMap.put(TAG_XMPMM_RENDITIONOFVERSIONID, "RenditionOfVersionID");
        _tagNameMap.put(TAG_XMPMM_SAVEID, "SaveID");
        _tagNameMap.put(TAG_XMPMM_VERSIONID, "VersionID");
        _tagNameMap.put(TAG_XMPMM_VERSIONSCOMMENTS, "VersionsComments");
        _tagNameMap.put(TAG_XMPMM_VERSIONSEVENTACTION, "VersionsEventAction");
        _tagNameMap.put(TAG_XMPMM_VERSIONSEVENTINSTANCEID, "VersionsEventInstanceID");
        _tagNameMap.put(TAG_XMPMM_VERSIONSEVENTSOFTWAREAGENT, "VersionsEventSoftwareAgent");
        _tagNameMap.put(TAG_XMPMM_VERSIONSMODIFIER, "VersionsModifier");
        _tagNameMap.put(TAG_XMPMM_VERSIONSVERSION, "VersionsVersion");

        // xmpNote
        _tagNameMap.put(TAG_XMPNOTE_HASEXTENDEDXMP, "HasExtendedXMP");

        // xmpPLUS
        _tagNameMap.put(TAG_XMPPLUS_CREDITLINEREQ, "CreditLineReq");

        // xmpRights
        _tagNameMap.put(TAG_XMPRIGHTS_CERTIFICATE, "Certificate");
        _tagNameMap.put(TAG_XMPRIGHTS_OWNER, "Owner");
        _tagNameMap.put(TAG_XMPRIGHTS_WEBSTATEMENT, "WebStatement");

        // xmpTPg
        _tagNameMap.put(TAG_XMPTPG_COLORANTS, "Colorants");
        _tagNameMap.put(TAG_XMPTPG_COLORANTB, "ColorantB");
        _tagNameMap.put(TAG_XMPTPG_COLORANTBLUE, "ColorantBlue");
        _tagNameMap.put(TAG_XMPTPG_COLORANTGRAY, "ColorantGray");
        _tagNameMap.put(TAG_XMPTPG_COLORANTL, "ColorantL");
        _tagNameMap.put(TAG_XMPTPG_COLORANTMODE, "ColorantMode");
        _tagNameMap.put(TAG_XMPTPG_COLORANTSWATCHNAME, "ColorantSwatchName");
        _tagNameMap.put(TAG_XMPTPG_COLORANTTYPE, "ColorantType");
        _tagNameMap.put(TAG_XMPTPG_FONTS, "Fonts");
        _tagNameMap.put(TAG_XMPTPG_FONTCOMPOSITE, "FontComposite");
        _tagNameMap.put(TAG_XMPTPG_FONTFAMILY, "FontFamily");
        _tagNameMap.put(TAG_XMPTPG_FONTNAME, "FontName");
        _tagNameMap.put(TAG_XMPTPG_FONTVERSION, "FontVersion");
        _tagNameMap.put(TAG_XMPTPG_HASVISIBLETRANSPARENCY, "HasVisibleTransparency");
        _tagNameMap.put(TAG_XMPTPG_MAXPAGESIZEH, "MaxPageSizeH");
        _tagNameMap.put(TAG_XMPTPG_MAXPAGESIZEW, "MaxPageSizeW");
        _tagNameMap.put(TAG_XMPTPG_PLATENAMES, "PlateNames");
        _tagNameMap.put(TAG_XMPTPG_SWATCHGROUPSCOLORANTS, "SwatchGroupsColorants");
        _tagNameMap.put(TAG_XMPTPG_SWATCHCOLORANTB, "SwatchColorantB");
        _tagNameMap.put(TAG_XMPTPG_SWATCHCOLORANTBLUE, "SwatchColorantBlue");
        _tagNameMap.put(TAG_XMPTPG_SWATCHCOLORANTGRAY, "SwatchColorantGray");
        _tagNameMap.put(TAG_XMPTPG_SWATCHCOLORANTL, "SwatchColorantL");
        _tagNameMap.put(TAG_XMPTPG_SWATCHCOLORANTMODE, "SwatchColorantMode");
        _tagNameMap.put(TAG_XMPTPG_SWATCHCOLORANTSWATCHNAME, "SwatchColorantSwatchName");
        _tagNameMap.put(TAG_XMPTPG_SWATCHCOLORANTTYPE, "SwatchColorantType");
        _tagNameMap.put(TAG_XMPTPG_SWATCHGROUPNAME, "SwatchGroupName");

        // XML
        _tagNameMap.put(TAG_XML_DC, "dc");

        // SVG
        _tagNameMap.put(TAG_SVG_HEIGHT, "height");
        _tagNameMap.put(TAG_SVG_METADATAID, "metadataId");
        _tagNameMap.put(TAG_SVG_WIDTH, "width");
    }

    @Nullable
    private XMPMeta _xmpMeta;

    public XmpDirectory()
    {
        this.setDescriptor(new XmpDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "XMP";
    }

    @Override
    @NotNull
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }

    /**
     * Gets a map of all XMP properties in this directory.
     * <p>
     * This is required because XMP properties are represented as strings, whereas the rest of this library
     * uses integers for keys.
     */
    @NotNull
    public Map<String, String> getXmpProperties()
    {
        Map<String, String> propertyValueByPath = new HashMap<String, String>();

        if (_xmpMeta != null)
        {
            try {
                for (Iterator i = _xmpMeta.iterator(); i.hasNext(); ) {
                    XMPPropertyInfo prop = (XMPPropertyInfo)i.next();
                    String path = prop.getPath();
                    String value = prop.getValue();
                    if (path != null && value != null) {
                        propertyValueByPath.put(path, value);
                    }
                }
            } catch (XMPException ignored) {
            }
        }

        return Collections.unmodifiableMap(propertyValueByPath);
    }

    public void setXMPMeta(@NotNull XMPMeta xmpMeta)
    {
        _xmpMeta = xmpMeta;

        try {
            int valueCount = 0;
            for (Iterator i = _xmpMeta.iterator(); i.hasNext(); ) {
                XMPPropertyInfo prop = (XMPPropertyInfo)i.next();
                if (prop.getPath() != null) {
                    valueCount++;
                }
            }
            setInt(TAG_XMP_VALUE_COUNT, valueCount);
        } catch (XMPException ignored) {
        }
    }

    /**
     * Gets the XMPMeta object used to populate this directory. It can be used for more XMP-oriented operations.
     * If one does not exist it will be created.
     */
    @NotNull
    public XMPMeta getXMPMeta()
    {
        if (_xmpMeta == null)
            _xmpMeta = new XMPMetaImpl();
        return _xmpMeta;
    }
}
