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

    // AAS
    public static final int TAG_XMP_AAS_AAS = "aas:aas".hashCode();
    public static final int TAG_XMP_AAS_ACDSEE = "aas:acdsee".hashCode();
    public static final int TAG_XMP_AAS_ALBUM = "aas:album".hashCode();
    public static final int TAG_XMP_AAS_APPLE_FI = "aas:apple-fi".hashCode();
    public static final int TAG_XMP_AAS_AUX = "aas:aux".hashCode();
    public static final int TAG_XMP_AAS_CC = "aas:cc".hashCode();
    public static final int TAG_XMP_AAS_CELL = "aas:cell".hashCode();
    public static final int TAG_XMP_AAS_CREATORATOM = "aas:creatorAtom".hashCode();
    public static final int TAG_XMP_AAS_CRS = "aas:crs".hashCode();
    public static final int TAG_XMP_AAS_DC = "aas:dc".hashCode();
    public static final int TAG_XMP_AAS_DEX = "aas:dex".hashCode();
    public static final int TAG_XMP_AAS_DICOM = "aas:DICOM".hashCode();
    public static final int TAG_XMP_AAS_DIGIKAM = "aas:digiKam".hashCode();
    public static final int TAG_XMP_AAS_DWC = "aas:dwc".hashCode();
    public static final int TAG_XMP_AAS_EXIF = "aas:exif".hashCode();
    public static final int TAG_XMP_AAS_EXIFEX = "aas:exifEX".hashCode();
    public static final int TAG_XMP_AAS_EXPRESSIONMEDIA = "aas:expressionmedia".hashCode();
    public static final int TAG_XMP_AAS_EXTENSIS = "aas:extensis".hashCode();
    public static final int TAG_XMP_AAS_FPV = "aas:fpv".hashCode();
    public static final int TAG_XMP_AAS_GAUDIO = "aas:GAudio".hashCode();
    public static final int TAG_XMP_AAS_GETTY = "aas:getty".hashCode();
    public static final int TAG_XMP_AAS_GIMAGE = "aas:GImage".hashCode();
    public static final int TAG_XMP_AAS_GPANO = "aas:GPano".hashCode();
    public static final int TAG_XMP_AAS_GSPHERICAL = "aas:GSpherical".hashCode();
    public static final int TAG_XMP_AAS_ICS = "aas:ics".hashCode();
    public static final int TAG_XMP_AAS_IPTCCORE = "aas:iptcCore".hashCode();
    public static final int TAG_XMP_AAS_IPTCEXT = "aas:iptcExt".hashCode();
    public static final int TAG_XMP_AAS_LR = "aas:lr".hashCode();
    public static final int TAG_XMP_AAS_MEDIAPRO = "aas:mediapro".hashCode();
    public static final int TAG_XMP_AAS_MICROSOFT = "aas:microsoft".hashCode();
    public static final int TAG_XMP_AAS_MP = "aas:MP".hashCode();
    public static final int TAG_XMP_AAS_MP1 = "aas:MP1".hashCode();
    public static final int TAG_XMP_AAS_MWG_COLL = "aas:mwg-coll".hashCode();
    public static final int TAG_XMP_AAS_MWG_KW = "aas:mwg-kw".hashCode();
    public static final int TAG_XMP_AAS_MWG_RS = "aas:mwg-rs".hashCode();
    public static final int TAG_XMP_AAS_PDF = "aas:pdf".hashCode();
    public static final int TAG_XMP_AAS_PDFX = "aas:pdfx".hashCode();
    public static final int TAG_XMP_AAS_PHOTOMECH = "aas:photomech".hashCode();
    public static final int TAG_XMP_AAS_PHOTOSHOP = "aas:photoshop".hashCode();
    public static final int TAG_XMP_AAS_PIXELLIVE = "aas:PixelLive".hashCode();
    public static final int TAG_XMP_AAS_PLUS = "aas:plus".hashCode();
    public static final int TAG_XMP_AAS_PMI = "aas:pmi".hashCode();
    public static final int TAG_XMP_AAS_PRISM = "aas:prism".hashCode();
    public static final int TAG_XMP_AAS_PRL = "aas:prl".hashCode();
    public static final int TAG_XMP_AAS_PRM = "aas:prm".hashCode();
    public static final int TAG_XMP_AAS_PUR = "aas:pur".hashCode();
    public static final int TAG_XMP_AAS_RDF = "aas:rdf".hashCode();
    public static final int TAG_XMP_AAS_SWF = "aas:swf".hashCode();
    public static final int TAG_XMP_AAS_TIFF = "aas:tiff".hashCode();
    public static final int TAG_XMP_AAS_X = "aas:x".hashCode();
    public static final int TAG_XMP_AAS_XMP = "aas:xmp".hashCode();
    public static final int TAG_XMP_AAS_XMPBJ = "aas:xmpBJ".hashCode();
    public static final int TAG_XMP_AAS_XMPDM = "aas:xmpDM".hashCode();
    public static final int TAG_XMP_AAS_XMPMM = "aas:xmpMM".hashCode();
    public static final int TAG_XMP_AAS_XMPNOTE = "aas:xmpNote".hashCode();
    public static final int TAG_XMP_AAS_XMPPLUS = "aas:xmpPLUS".hashCode();
    public static final int TAG_XMP_AAS_XMPRIGHTS = "aas:xmpRights".hashCode();
    public static final int TAG_XMP_AAS_XMPTPG = "aas:xmpTPg".hashCode();

    //ACDSEE
    public static final int TAG_XMP_ACDSEE_AUTHOR = "acdsee:Author".hashCode();
    public static final int TAG_XMP_ACDSEE_CAPTION = "acdsee:Caption".hashCode();
    public static final int TAG_XMP_ACDSEE_CATEGORIES = "acdsee:Categories".hashCode();
    public static final int TAG_XMP_ACDSEE_COLLECTIONS = "acdsee:Collections".hashCode();
    public static final int TAG_XMP_ACDSEE_DATETIME = "acdsee:DateTime".hashCode();
    public static final int TAG_XMP_ACDSEE_DPP = "acdsee:DPP".hashCode();
    public static final int TAG_XMP_ACDSEE_EDITSTATUS = "acdsee:EditStatus".hashCode();
    public static final int TAG_XMP_ACDSEE_FIXTUREIDENTIFIER = "acdsee:FixtureIdentifier".hashCode();
    public static final int TAG_XMP_ACDSEE_KEYWORDS = "acdsee:Keywords".hashCode();
    public static final int TAG_XMP_ACDSEE_NOTES = "acdsee:Notes".hashCode();
    public static final int TAG_XMP_ACDSEE_OBJECTCYCLE = "acdsee:ObjectCycle".hashCode();
    public static final int TAG_XMP_ACDSEE_ORIGINATINGPROGRAM = "acdsee:OriginatingProgram".hashCode();
    public static final int TAG_XMP_ACDSEE_RATING = "acdsee:Rating".hashCode();
    public static final int TAG_XMP_ACDSEE_RAWRPPUSED = "acdsee:Rawrppused".hashCode();
    public static final int TAG_XMP_ACDSEE_RELEASEDATE = "acdsee:ReleaseDate".hashCode();
    public static final int TAG_XMP_ACDSEE_RELEASETIME = "acdsee:ReleaseTime".hashCode();
    public static final int TAG_XMP_ACDSEE_RPP = "acdsee:RPP".hashCode();
    public static final int TAG_XMP_ACDSEE_SNAPSHOTS = "acdsee:Snapshots".hashCode();
    public static final int TAG_XMP_ACDSEE_TAGGED = "acdsee:Tagged".hashCode();

    // Album
    public static final int TAG_XMP_ALBUM_NOTES = "album:Notes".hashCode();

    // Apple_fi
    public static final int TAG_XMP_APPLE_FI_ANGLEINFOROLL = "apple_fi:AngleInfoRoll".hashCode();
    public static final int TAG_XMP_APPLE_FI_ANGLEINFOYAW = "apple_fi:AngleInfoYaw".hashCode();
    public static final int TAG_XMP_APPLE_FI_CONFIDENCELEVEL = "apple_fi:ConfidenceLevel".hashCode();
    public static final int TAG_XMP_APPLE_FI_FACEID = "apple_fi:FaceID".hashCode();
    public static final int TAG_XMP_APPLE_FI_TIMESTAMP = "apple_fi:TimeStamp".hashCode();

    // Aux
    public static final int TAG_XMP_AUX_APPROXIMATEFOCUSDISTANCE = "aux:ApproximateFocusDistance".hashCode();
    public static final int TAG_XMP_AUX_DISTORTIONCORRECTIONALREADYAPPLIED = "aux:DistortionCorrectionAlreadyApplied".hashCode();
    public static final int TAG_XMP_AUX_FIRMWARE = "aux:Firmware".hashCode();
    public static final int TAG_XMP_AUX_FLASHCOMPENSATION = "aux:FlashCompensation".hashCode();
    public static final int TAG_XMP_AUX_IMAGENUMBER = "aux:ImageNumber".hashCode();
    public static final int TAG_XMP_AUX_ISMERGEDHDR = "aux:IsMergedHDR".hashCode();
    public static final int TAG_XMP_AUX_ISMERGEDPANORAMA = "aux:IsMergedPanorama".hashCode();
    public static final int TAG_XMP_AUX_LATERALCHROMATICABERRATIONCORRECTIONALREADYAPPLIED = "aux:LateralChromaticAberrationCorrectionAlreadyApplied".hashCode();
    public static final int TAG_XMP_AUX_LENS = "aux:Lens".hashCode();
    public static final int TAG_XMP_AUX_LENSDISTORTINFO = "aux:LensDistortInfo".hashCode();
    public static final int TAG_XMP_AUX_LENSID = "aux:LensID".hashCode();
    public static final int TAG_XMP_AUX_LENSINFO = "aux:LensInfo".hashCode();
    public static final int TAG_XMP_AUX_LENSSERIALNUMBER = "aux:LensSerialNumber".hashCode();
    public static final int TAG_XMP_AUX_OWNERNAME = "aux:OwnerName".hashCode();
    public static final int TAG_XMP_AUX_SERIALNUMBER = "aux:SerialNumber".hashCode();
    public static final int TAG_XMP_AUX_VIGNETTECORRECTIONALREADYAPPLIED = "aux:VignetteCorrectionAlreadyApplied".hashCode();

    // CC
    public static final int TAG_XMP_CC_ATTRIBUTIONNAME = "cc:AttributionName".hashCode();
    public static final int TAG_XMP_CC_ATTRIBUTIONURL = "cc:AttributionURL".hashCode();
    public static final int TAG_XMP_CC_DEPRECATEDON = "cc:DeprecatedOn".hashCode();
    public static final int TAG_XMP_CC_JURISDICTION = "cc:Jurisdiction".hashCode();
    public static final int TAG_XMP_CC_LEGALCODE = "cc:LegalCode".hashCode();
    public static final int TAG_XMP_CC_LICENSE = "cc:License".hashCode();
    public static final int TAG_XMP_CC_MOREPERMISSIONS = "cc:MorePermissions".hashCode();
    public static final int TAG_XMP_CC_PERMITS = "cc:Permits".hashCode();
    public static final int TAG_XMP_CC_PROHIBITS = "cc:Prohibits".hashCode();
    public static final int TAG_XMP_CC_REQUIRES = "cc:Requires".hashCode();
    public static final int TAG_XMP_CC_USEGUIDELINES = "cc:UseGuidelines".hashCode();

    // Cell
    public static final int TAG_XMP_CELL_CELLTOWERID = "cell:CellTowerID".hashCode();
    public static final int TAG_XMP_CELL_CELLGLOBALID = "cell:CellGlobalID".hashCode();
    public static final int TAG_XMP_CELL_LOCATIONAREACODE = "cell:LocationAreaCode".hashCode();
    public static final int TAG_XMP_CELL_MOBILECOUNTRYCODE = "cell:MobileCountryCode".hashCode();
    public static final int TAG_XMP_CELL_MOBILENETWORKCODE = "cell:MobileNetworkCode".hashCode();
    public static final int TAG_XMP_CELL_CELLR = "cell:CellR".hashCode();

    // CreatorAtom
    public static final int TAG_XMP_CREATORATOM_AEPROJECTLINK = "creatorAtom:AeProjectLink".hashCode();
    public static final int TAG_XMP_CREATORATOM_AEPROJECTLINKCOMPOSITIONID = "creatorAtom:AeProjectLinkCompositionID".hashCode();
    public static final int TAG_XMP_CREATORATOM_AEPROJECTLINKFULLPATH = "creatorAtom:AeProjectLinkFullPath".hashCode();
    public static final int TAG_XMP_CREATORATOM_AEPROJECTLINKRENDEROUTPUTMODULEINDEX = "creatorAtom:AeProjectLinkRenderOutputModuleIndex".hashCode();
    public static final int TAG_XMP_CREATORATOM_AEPROJECTLINKRENDERQUEUEITEMID = "creatorAtom:AeProjectLinkRenderQueueItemID".hashCode();
    public static final int TAG_XMP_CREATORATOM_AEPROJECTLINKRENDERTIMESTAMP = "creatorAtom:AeProjectLinkRenderTimeStamp".hashCode();
    public static final int TAG_XMP_CREATORATOM_MACATOM = "creatorAtom:MacAtom".hashCode();
    public static final int TAG_XMP_CREATORATOM_MACATOMAPPLICATIONCODE = "creatorAtom:MacAtomApplicationCode".hashCode();
    public static final int TAG_XMP_CREATORATOM_MACATOMINVOCATIONAPPLEEVENT = "creatorAtom:MacAtomInvocationAppleEvent".hashCode();
    public static final int TAG_XMP_CREATORATOM_MACATOMPOSIXPROJECTPATH = "creatorAtom:MacAtomPosixProjectPath".hashCode();
    public static final int TAG_XMP_CREATORATOM_WINDOWSATOM = "creatorAtom:WindowsAtom".hashCode();
    public static final int TAG_XMP_CREATORATOM_WINDOWSATOMEXTENSION = "creatorAtom:WindowsAtomExtension".hashCode();
    public static final int TAG_XMP_CREATORATOM_WINDOWSATOMINVOCATIONFLAGS = "creatorAtom:WindowsAtomInvocationFlags".hashCode();
    public static final int TAG_XMP_CREATORATOM_WINDOWSATOMUNCPROJECTPATH = "creatorAtom:WindowsAtomUncProjectPath".hashCode();

    // CRS
    public static final int TAG_XMP_CRS_ALREADYAPPLIED = "crs:AlreadyApplied".hashCode();
    public static final int TAG_XMP_CRS_AUTOBRIGHTNESS = "crs:AutoBrightness".hashCode();
    public static final int TAG_XMP_CRS_AUTOCONTRAST = "crs:AutoContrast".hashCode();
    public static final int TAG_XMP_CRS_AUTOEXPOSURE = "crs:AutoExposure".hashCode();
    public static final int TAG_XMP_CRS_AUTOLATERALCA = "crs:AutoLateralCA".hashCode();
    public static final int TAG_XMP_CRS_AUTOSHADOWS = "crs:AutoShadows".hashCode();
    public static final int TAG_XMP_CRS_AUTOWHITEVERSION = "crs:AutoWhiteVersion".hashCode();
    public static final int TAG_XMP_CRS_BLACKS2012 = "crs:Blacks2012".hashCode();
    public static final int TAG_XMP_CRS_BLUEHUE = "crs:BlueHue".hashCode();
    public static final int TAG_XMP_CRS_BLUESATURATION = "crs:BlueSaturation".hashCode();
    public static final int TAG_XMP_CRS_BRIGHTNESS = "crs:Brightness".hashCode();
    public static final int TAG_XMP_CRS_CAMERAPROFILE = "crs:CameraProfile".hashCode();
    public static final int TAG_XMP_CRS_CAMERAPROFILEDIGEST = "crs:CameraProfileDigest".hashCode();
    public static final int TAG_XMP_CRS_CHROMATICABERRATIONB = "crs:ChromaticAberrationB".hashCode();
    public static final int TAG_XMP_CRS_CHROMATICABERRATIONR = "crs:ChromaticAberrationR".hashCode();
    public static final int TAG_XMP_CRS_CIRCULARGRADIENTBASEDCORRECTIONS = "crs:CircularGradientBasedCorrections".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRACTIVE = "crs:CircGradBasedCorrActive".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRAMOUNT = "crs:CircGradBasedCorrAmount".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKS = "crs:CircGradBasedCorrMasks".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKALPHA = "crs:CircGradBasedCorrMaskAlpha".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKANGLE = "crs:CircGradBasedCorrMaskAngle".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKBOTTOM = "crs:CircGradBasedCorrMaskBottom".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKCENTERVALUE = "crs:CircGradBasedCorrMaskCenterValue".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKCENTERWEIGHT = "crs:CircGradBasedCorrMaskCenterWeight".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKDABS = "crs:CircGradBasedCorrMaskDabs".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFEATHER = "crs:CircGradBasedCorrMaskFeather".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFLIPPED = "crs:CircGradBasedCorrMaskFlipped".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFLOW = "crs:CircGradBasedCorrMaskFlow".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFULLX = "crs:CircGradBasedCorrMaskFullX".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFULLY = "crs:CircGradBasedCorrMaskFullY".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKLEFT = "crs:CircGradBasedCorrMaskLeft".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKVALUE = "crs:CircGradBasedCorrMaskValue".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKMIDPOINT = "crs:CircGradBasedCorrMaskMidpoint".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKPERIMETERVALUE = "crs:CircGradBasedCorrMaskPerimeterValue".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKRADIUS = "crs:CircGradBasedCorrMaskRadius".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKRIGHT = "crs:CircGradBasedCorrMaskRight".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKROUNDNESS = "crs:CircGradBasedCorrMaskRoundness".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKSIZEX = "crs:CircGradBasedCorrMaskSizeX".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKSIZEY = "crs:CircGradBasedCorrMaskSizeY".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKTOP = "crs:CircGradBasedCorrMaskTop".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKVERSION = "crs:CircGradBasedCorrMaskVersion".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKWHAT = "crs:CircGradBasedCorrMaskWhat".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKX = "crs:CircGradBasedCorrMaskX".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKY = "crs:CircGradBasedCorrMaskY".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKZEROX = "crs:CircGradBasedCorrMaskZeroX".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMASKZEROY = "crs:CircGradBasedCorrMaskZeroY".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRBRIGHTNESS = "crs:CircGradBasedCorrBrightness".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRCLARITY = "crs:CircGradBasedCorrClarity".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRCLARITY2012 = "crs:CircGradBasedCorrClarity2012".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRCONTRAST = "crs:CircGradBasedCorrContrast".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRCONTRAST2012 = "crs:CircGradBasedCorrContrast2012".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRDEFRINGE = "crs:CircGradBasedCorrDefringe".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORREXPOSURE = "crs:CircGradBasedCorrExposure".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORREXPOSURE2012 = "crs:CircGradBasedCorrExposure2012".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRHIGHLIGHTS2012 = "crs:CircGradBasedCorrHighlights2012".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRLUMINANCENOISE = "crs:CircGradBasedCorrLuminanceNoise".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRMOIRE = "crs:CircGradBasedCorrMoire".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRSHADOWS2012 = "crs:CircGradBasedCorrShadows2012".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRSHARPNESS = "crs:CircGradBasedCorrSharpness".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRTEMPERATURE = "crs:CircGradBasedCorrTemperature".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRTINT = "crs:CircGradBasedCorrTint".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRHUE = "crs:CircGradBasedCorrHue".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRSATURATION = "crs:CircGradBasedCorrSaturation".hashCode();
    public static final int TAG_XMP_CRS_CIRCGRADBASEDCORRWHAT = "crs:CircGradBasedCorrWhat".hashCode();
    public static final int TAG_XMP_CRS_CLARITY = "crs:Clarity".hashCode();
    public static final int TAG_XMP_CRS_CLARITY2012 = "crs:Clarity2012".hashCode();
    public static final int TAG_XMP_CRS_COLORNOISEREDUCTION = "crs:ColorNoiseReduction".hashCode();
    public static final int TAG_XMP_CRS_COLORNOISEREDUCTIONDETAIL = "crs:ColorNoiseReductionDetail".hashCode();
    public static final int TAG_XMP_CRS_COLORNOISEREDUCTIONSMOOTHNESS = "crs:ColorNoiseReductionSmoothness".hashCode();
    public static final int TAG_XMP_CRS_CONTRAST = "crs:Contrast".hashCode();
    public static final int TAG_XMP_CRS_CONTRAST2012 = "crs:Contrast2012".hashCode();
    public static final int TAG_XMP_CRS_CONVERTER = "crs:Converter".hashCode();
    public static final int TAG_XMP_CRS_CONVERTTOGRAYSCALE = "crs:ConvertToGrayscale".hashCode();
    public static final int TAG_XMP_CRS_CROPANGLE = "crs:CropAngle".hashCode();
    public static final int TAG_XMP_CRS_CROPBOTTOM = "crs:CropBottom".hashCode();
    public static final int TAG_XMP_CRS_CROPCONSTRAINTOWARP = "crs:CropConstrainToWarp".hashCode();
    public static final int TAG_XMP_CRS_CROPHEIGHT = "crs:CropHeight".hashCode();
    public static final int TAG_XMP_CRS_CROPLEFT = "crs:CropLeft".hashCode();
    public static final int TAG_XMP_CRS_CROPRIGHT = "crs:CropRight".hashCode();
    public static final int TAG_XMP_CRS_CROPTOP = "crs:CropTop".hashCode();
    public static final int TAG_XMP_CRS_CROPUNIT = "crs:CropUnit".hashCode();
    public static final int TAG_XMP_CRS_CROPUNITS = "crs:CropUnits".hashCode();
    public static final int TAG_XMP_CRS_CROPWIDTH = "crs:CropWidth".hashCode();
    public static final int TAG_XMP_CRS_DEFAULTAUTOGRAY = "crs:DefaultAutoGray".hashCode();
    public static final int TAG_XMP_CRS_DEFAULTAUTOTONE = "crs:DefaultAutoTone".hashCode();
    public static final int TAG_XMP_CRS_DEFAULTSSPECIFICTOISO = "crs:DefaultsSpecificToISO".hashCode();
    public static final int TAG_XMP_CRS_DEFAULTSSPECIFICTOSERIAL = "crs:DefaultsSpecificToSerial".hashCode();
    public static final int TAG_XMP_CRS_DEFRINGE = "crs:Defringe".hashCode();
    public static final int TAG_XMP_CRS_DEFRINGEGREENAMOUNT = "crs:DefringeGreenAmount".hashCode();
    public static final int TAG_XMP_CRS_DEFRINGEGREENHUEHI = "crs:DefringeGreenHueHi".hashCode();
    public static final int TAG_XMP_CRS_DEFRINGEGREENHUELO = "crs:DefringeGreenHueLo".hashCode();
    public static final int TAG_XMP_CRS_DEFRINGEPURPLEAMOUNT = "crs:DefringePurpleAmount".hashCode();
    public static final int TAG_XMP_CRS_DEFRINGEPURPLEHUEHI = "crs:DefringePurpleHueHi".hashCode();
    public static final int TAG_XMP_CRS_DEFRINGEPURPLEHUELO = "crs:DefringePurpleHueLo".hashCode();
    public static final int TAG_XMP_CRS_DEHAZE = "crs:Dehaze".hashCode();
    public static final int TAG_XMP_CRS_DNGIGNORESIDECARS = "crs:DNGIgnoreSidecars".hashCode();
    public static final int TAG_XMP_CRS_EXPOSURE = "crs:Exposure".hashCode();
    public static final int TAG_XMP_CRS_EXPOSURE2012 = "crs:Exposure2012".hashCode();
    public static final int TAG_XMP_CRS_FILLLIGHT = "crs:FillLight".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRECTIONS = "crs:GradientBasedCorrections".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRACTIVE = "crs:GradientBasedCorrActive".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRAMOUNT = "crs:GradientBasedCorrAmount".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKS = "crs:GradientBasedCorrMasks".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKALPHA = "crs:GradientBasedCorrMaskAlpha".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKANGLE = "crs:GradientBasedCorrMaskAngle".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKBOTTOM = "crs:GradientBasedCorrMaskBottom".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKCENTERVALUE = "crs:GradientBasedCorrMaskCenterValue".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKCENTERWEIGHT = "crs:GradientBasedCorrMaskCenterWeight".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKDABS = "crs:GradientBasedCorrMaskDabs".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKFEATHER = "crs:GradientBasedCorrMaskFeather".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKFLIPPED = "crs:GradientBasedCorrMaskFlipped".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKFLOW = "crs:GradientBasedCorrMaskFlow".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKFULLX = "crs:GradientBasedCorrMaskFullX".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKFULLY = "crs:GradientBasedCorrMaskFullY".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKLEFT = "crs:GradientBasedCorrMaskLeft".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKVALUE = "crs:GradientBasedCorrMaskValue".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKMIDPOINT = "crs:GradientBasedCorrMaskMidpoint".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKPERIMETERVALUE = "crs:GradientBasedCorrMaskPerimeterValue".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKRADIUS = "crs:GradientBasedCorrMaskRadius".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKRIGHT = "crs:GradientBasedCorrMaskRight".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKROUNDNESS = "crs:GradientBasedCorrMaskRoundness".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKSIZEX = "crs:GradientBasedCorrMaskSizeX".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKSIZEY = "crs:GradientBasedCorrMaskSizeY".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKTOP = "crs:GradientBasedCorrMaskTop".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKVERSION = "crs:GradientBasedCorrMaskVersion".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKWHAT = "crs:GradientBasedCorrMaskWhat".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKX = "crs:GradientBasedCorrMaskX".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKY = "crs:GradientBasedCorrMaskY".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKZEROX = "crs:GradientBasedCorrMaskZeroX".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMASKZEROY = "crs:GradientBasedCorrMaskZeroY".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRBRIGHTNESS = "crs:GradientBasedCorrBrightness".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRCLARITY = "crs:GradientBasedCorrClarity".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRCLARITY2012 = "crs:GradientBasedCorrClarity2012".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRCONTRAST = "crs:GradientBasedCorrContrast".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRCONTRAST2012 = "crs:GradientBasedCorrContrast2012".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRDEFRINGE = "crs:GradientBasedCorrDefringe".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORREXPOSURE = "crs:GradientBasedCorrExposure".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORREXPOSURE2012 = "crs:GradientBasedCorrExposure2012".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRHIGHLIGHTS2012 = "crs:GradientBasedCorrHighlights2012".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRLUMINANCENOISE = "crs:GradientBasedCorrLuminanceNoise".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRMOIRE = "crs:GradientBasedCorrMoire".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRSHADOWS2012 = "crs:GradientBasedCorrShadows2012".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRSHARPNESS = "crs:GradientBasedCorrSharpness".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRTEMPERATURE = "crs:GradientBasedCorrTemperature".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRTINT = "crs:GradientBasedCorrTint".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRHUE = "crs:GradientBasedCorrHue".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRSATURATION = "crs:GradientBasedCorrSaturation".hashCode();
    public static final int TAG_XMP_CRS_GRADIENTBASEDCORRWHAT = "crs:GradientBasedCorrWhat".hashCode();
    public static final int TAG_XMP_CRS_GRAINAMOUNT = "crs:GrainAmount".hashCode();
    public static final int TAG_XMP_CRS_GRAINFREQUENCY = "crs:GrainFrequency".hashCode();
    public static final int TAG_XMP_CRS_GRAINSIZE = "crs:GrainSize".hashCode();
    public static final int TAG_XMP_CRS_GRAYMIXERAQUA = "crs:GrayMixerAqua".hashCode();
    public static final int TAG_XMP_CRS_GRAYMIXERBLUE = "crs:GrayMixerBlue".hashCode();
    public static final int TAG_XMP_CRS_GRAYMIXERGREEN = "crs:GrayMixerGreen".hashCode();
    public static final int TAG_XMP_CRS_GRAYMIXERMAGENTA = "crs:GrayMixerMagenta".hashCode();
    public static final int TAG_XMP_CRS_GRAYMIXERORANGE = "crs:GrayMixerOrange".hashCode();
    public static final int TAG_XMP_CRS_GRAYMIXERPURPLE = "crs:GrayMixerPurple".hashCode();
    public static final int TAG_XMP_CRS_GRAYMIXERRED = "crs:GrayMixerRed".hashCode();
    public static final int TAG_XMP_CRS_GRAYMIXERYELLOW = "crs:GrayMixerYellow".hashCode();
    public static final int TAG_XMP_CRS_GREENHUE = "crs:GreenHue".hashCode();
    public static final int TAG_XMP_CRS_GREENSATURATION = "crs:GreenSaturation".hashCode();
    public static final int TAG_XMP_CRS_HASCROP = "crs:HasCrop".hashCode();
    public static final int TAG_XMP_CRS_HASSETTINGS = "crs:HasSettings".hashCode();
    public static final int TAG_XMP_CRS_HIGHLIGHTRECOVERY = "crs:HighlightRecovery".hashCode();
    public static final int TAG_XMP_CRS_HIGHLIGHTS2012 = "crs:Highlights2012".hashCode();
    public static final int TAG_XMP_CRS_HUEADJUSTMENTAQUA = "crs:HueAdjustmentAqua".hashCode();
    public static final int TAG_XMP_CRS_HUEADJUSTMENTBLUE = "crs:HueAdjustmentBlue".hashCode();
    public static final int TAG_XMP_CRS_HUEADJUSTMENTGREEN = "crs:HueAdjustmentGreen".hashCode();
    public static final int TAG_XMP_CRS_HUEADJUSTMENTMAGENTA = "crs:HueAdjustmentMagenta".hashCode();
    public static final int TAG_XMP_CRS_HUEADJUSTMENTORANGE = "crs:HueAdjustmentOrange".hashCode();
    public static final int TAG_XMP_CRS_HUEADJUSTMENTPURPLE = "crs:HueAdjustmentPurple".hashCode();
    public static final int TAG_XMP_CRS_HUEADJUSTMENTRED = "crs:HueAdjustmentRed".hashCode();
    public static final int TAG_XMP_CRS_HUEADJUSTMENTYELLOW = "crs:HueAdjustmentYellow".hashCode();
    public static final int TAG_XMP_CRS_INCREMENTALTEMPERATURE = "crs:IncrementalTemperature".hashCode();
    public static final int TAG_XMP_CRS_INCREMENTALTINT = "crs:IncrementalTint".hashCode();
    public static final int TAG_XMP_CRS_JPEGHANDLING = "crs:JPEGHandling".hashCode();
    public static final int TAG_XMP_CRS_LENSMANUALDISTORTIONAMOUNT = "crs:LensManualDistortionAmount".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILECHROMATICABERRATIONSCALE = "crs:LensProfileChromaticAberrationScale".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEDIGEST = "crs:LensProfileDigest".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEDISTORTIONSCALE = "crs:LensProfileDistortionScale".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEENABLE = "crs:LensProfileEnable".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEFILENAME = "crs:LensProfileFilename".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEMATCHKEYCAMERAMODELNAME = "crs:LensProfileMatchKeyCameraModelName".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEMATCHKEYEXIFMAKE = "crs:LensProfileMatchKeyExifMake".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEMATCHKEYEXIFMODEL = "crs:LensProfileMatchKeyExifModel".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEMATCHKEYISRAW = "crs:LensProfileMatchKeyIsRaw".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEMATCHKEYLENSID = "crs:LensProfileMatchKeyLensID".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEMATCHKEYLENSINFO = "crs:LensProfileMatchKeyLensInfo".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEMATCHKEYLENSNAME = "crs:LensProfileMatchKeyLensName".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEMATCHKEYSENSORFORMATFACTOR = "crs:LensProfileMatchKeySensorFormatFactor".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILENAME = "crs:LensProfileName".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILESETUP = "crs:LensProfileSetup".hashCode();
    public static final int TAG_XMP_CRS_LENSPROFILEVIGNETTINGSCALE = "crs:LensProfileVignettingScale".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCEADJUSTMENTAQUA = "crs:LuminanceAdjustmentAqua".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCEADJUSTMENTBLUE = "crs:LuminanceAdjustmentBlue".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCEADJUSTMENTGREEN = "crs:LuminanceAdjustmentGreen".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCEADJUSTMENTMAGENTA = "crs:LuminanceAdjustmentMagenta".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCEADJUSTMENTORANGE = "crs:LuminanceAdjustmentOrange".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCEADJUSTMENTPURPLE = "crs:LuminanceAdjustmentPurple".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCEADJUSTMENTRED = "crs:LuminanceAdjustmentRed".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCEADJUSTMENTYELLOW = "crs:LuminanceAdjustmentYellow".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCENOISEREDUCTIONCONTRAST = "crs:LuminanceNoiseReductionContrast".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCENOISEREDUCTIONDETAIL = "crs:LuminanceNoiseReductionDetail".hashCode();
    public static final int TAG_XMP_CRS_LUMINANCESMOOTHING = "crs:LuminanceSmoothing".hashCode();
    public static final int TAG_XMP_CRS_MOIREFILTER = "crs:MoireFilter".hashCode();
    public static final int TAG_XMP_CRS_NEGATIVECACHELARGEPREVIEWSIZE = "crs:NegativeCacheLargePreviewSize".hashCode();
    public static final int TAG_XMP_CRS_NEGATIVECACHEMAXIMUMSIZE = "crs:NegativeCacheMaximumSize".hashCode();
    public static final int TAG_XMP_CRS_NEGATIVECACHEPATH = "crs:NegativeCachePath".hashCode();
    public static final int TAG_XMP_CRS_PAINTBASEDCORRECTIONS = "crs:PaintBasedCorrections".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONACTIVE = "crs:PaintCorrectionActive".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONAMOUNT = "crs:PaintCorrectionAmount".hashCode();
    public static final int TAG_XMP_CRS_PAINTBASEDCORRECTIONMASKS = "crs:PaintBasedCorrectionMasks".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKALPHA = "crs:PaintCorrectionMaskAlpha".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKANGLE = "crs:PaintCorrectionMaskAngle".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKBOTTOM = "crs:PaintCorrectionMaskBottom".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKCENTERVALUE = "crs:PaintCorrectionMaskCenterValue".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKCENTERWEIGHT = "crs:PaintCorrectionMaskCenterWeight".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKDABS = "crs:PaintCorrectionMaskDabs".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKFEATHER = "crs:PaintCorrectionMaskFeather".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKFLIPPED = "crs:PaintCorrectionMaskFlipped".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKFLOW = "crs:PaintCorrectionMaskFlow".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKFULLX = "crs:PaintCorrectionMaskFullX".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKFULLY = "crs:PaintCorrectionMaskFullY".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKLEFT = "crs:PaintCorrectionMaskLeft".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKVALUE = "crs:PaintCorrectionMaskValue".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKMIDPOINT = "crs:PaintCorrectionMaskMidpoint".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKPERIMETERVALUE = "crs:PaintCorrectionMaskPerimeterValue".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKRADIUS = "crs:PaintCorrectionMaskRadius".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKRIGHT = "crs:PaintCorrectionMaskRight".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKROUNDNESS = "crs:PaintCorrectionMaskRoundness".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKSIZEX = "crs:PaintCorrectionMaskSizeX".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKSIZEY = "crs:PaintCorrectionMaskSizeY".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKTOP = "crs:PaintCorrectionMaskTop".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKVERSION = "crs:PaintCorrectionMaskVersion".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKWHAT = "crs:PaintCorrectionMaskWhat".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKX = "crs:PaintCorrectionMaskX".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKY = "crs:PaintCorrectionMaskY".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKZEROX = "crs:PaintCorrectionMaskZeroX".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMASKZEROY = "crs:PaintCorrectionMaskZeroY".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONBRIGHTNESS = "crs:PaintCorrectionBrightness".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONCLARITY = "crs:PaintCorrectionClarity".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONCLARITY2012 = "crs:PaintCorrectionClarity2012".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONCONTRAST = "crs:PaintCorrectionContrast".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONCONTRAST2012 = "crs:PaintCorrectionContrast2012".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONDEFRINGE = "crs:PaintCorrectionDefringe".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONEXPOSURE = "crs:PaintCorrectionExposure".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONEXPOSURE2012 = "crs:PaintCorrectionExposure2012".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONHIGHLIGHTS2012 = "crs:PaintCorrectionHighlights2012".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONLUMINANCENOISE = "crs:PaintCorrectionLuminanceNoise".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONMOIRE = "crs:PaintCorrectionMoire".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONSHADOWS2012 = "crs:PaintCorrectionShadows2012".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONSHARPNESS = "crs:PaintCorrectionSharpness".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONTEMPERATURE = "crs:PaintCorrectionTemperature".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONTINT = "crs:PaintCorrectionTint".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONHUE = "crs:PaintCorrectionHue".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONSATURATION = "crs:PaintCorrectionSaturation".hashCode();
    public static final int TAG_XMP_CRS_PAINTCORRECTIONWHAT = "crs:PaintCorrectionWhat".hashCode();
    public static final int TAG_XMP_CRS_PARAMETRICDARKS = "crs:ParametricDarks".hashCode();
    public static final int TAG_XMP_CRS_PARAMETRICHIGHLIGHTS = "crs:ParametricHighlights".hashCode();
    public static final int TAG_XMP_CRS_PARAMETRICHIGHLIGHTSPLIT = "crs:ParametricHighlightSplit".hashCode();
    public static final int TAG_XMP_CRS_PARAMETRICLIGHTS = "crs:ParametricLights".hashCode();
    public static final int TAG_XMP_CRS_PARAMETRICMIDTONESPLIT = "crs:ParametricMidtoneSplit".hashCode();
    public static final int TAG_XMP_CRS_PARAMETRICSHADOWS = "crs:ParametricShadows".hashCode();
    public static final int TAG_XMP_CRS_PARAMETRICSHADOWSPLIT = "crs:ParametricShadowSplit".hashCode();
    public static final int TAG_XMP_CRS_PERSPECTIVEASPECT = "crs:PerspectiveAspect".hashCode();
    public static final int TAG_XMP_CRS_PERSPECTIVEHORIZONTAL = "crs:PerspectiveHorizontal".hashCode();
    public static final int TAG_XMP_CRS_PERSPECTIVEROTATE = "crs:PerspectiveRotate".hashCode();
    public static final int TAG_XMP_CRS_PERSPECTIVESCALE = "crs:PerspectiveScale".hashCode();
    public static final int TAG_XMP_CRS_PERSPECTIVEUPRIGHT = "crs:PerspectiveUpright".hashCode();
    public static final int TAG_XMP_CRS_PERSPECTIVEVERTICAL = "crs:PerspectiveVertical".hashCode();
    public static final int TAG_XMP_CRS_PERSPECTIVEX = "crs:PerspectiveX".hashCode();
    public static final int TAG_XMP_CRS_PERSPECTIVEY = "crs:PerspectiveY".hashCode();
    public static final int TAG_XMP_CRS_POSTCROPVIGNETTEAMOUNT = "crs:PostCropVignetteAmount".hashCode();
    public static final int TAG_XMP_CRS_POSTCROPVIGNETTEFEATHER = "crs:PostCropVignetteFeather".hashCode();
    public static final int TAG_XMP_CRS_POSTCROPVIGNETTEHIGHLIGHTCONTRAST = "crs:PostCropVignetteHighlightContrast".hashCode();
    public static final int TAG_XMP_CRS_POSTCROPVIGNETTEMIDPOINT = "crs:PostCropVignetteMidpoint".hashCode();
    public static final int TAG_XMP_CRS_POSTCROPVIGNETTEROUNDNESS = "crs:PostCropVignetteRoundness".hashCode();
    public static final int TAG_XMP_CRS_POSTCROPVIGNETTESTYLE = "crs:PostCropVignetteStyle".hashCode();
    public static final int TAG_XMP_CRS_PROCESSVERSION = "crs:ProcessVersion".hashCode();
    public static final int TAG_XMP_CRS_RAWFILENAME = "crs:RawFileName".hashCode();
    public static final int TAG_XMP_CRS_REDEYEINFO = "crs:RedEyeInfo".hashCode();
    public static final int TAG_XMP_CRS_REDHUE = "crs:RedHue".hashCode();
    public static final int TAG_XMP_CRS_REDSATURATION = "crs:RedSaturation".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAS = "crs:RetouchAreas".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAFEATHER = "crs:RetouchAreaFeather".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKS = "crs:RetouchAreaMasks".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKALPHA = "crs:RetouchAreaMaskAlpha".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKANGLE = "crs:RetouchAreaMaskAngle".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKBOTTOM = "crs:RetouchAreaMaskBottom".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKCENTERVALUE = "crs:RetouchAreaMaskCenterValue".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKCENTERWEIGHT = "crs:RetouchAreaMaskCenterWeight".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKDABS = "crs:RetouchAreaMaskDabs".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKFEATHER = "crs:RetouchAreaMaskFeather".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKFLIPPED = "crs:RetouchAreaMaskFlipped".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKFLOW = "crs:RetouchAreaMaskFlow".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKFULLX = "crs:RetouchAreaMaskFullX".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKFULLY = "crs:RetouchAreaMaskFullY".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKLEFT = "crs:RetouchAreaMaskLeft".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKVALUE = "crs:RetouchAreaMaskValue".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKMIDPOINT = "crs:RetouchAreaMaskMidpoint".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKPERIMETERVALUE = "crs:RetouchAreaMaskPerimeterValue".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKRADIUS = "crs:RetouchAreaMaskRadius".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKRIGHT = "crs:RetouchAreaMaskRight".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKROUNDNESS = "crs:RetouchAreaMaskRoundness".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKSIZEX = "crs:RetouchAreaMaskSizeX".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKSIZEY = "crs:RetouchAreaMaskSizeY".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKTOP = "crs:RetouchAreaMaskTop".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKVERSION = "crs:RetouchAreaMaskVersion".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKWHAT = "crs:RetouchAreaMaskWhat".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKX = "crs:RetouchAreaMaskX".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKY = "crs:RetouchAreaMaskY".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKZEROX = "crs:RetouchAreaMaskZeroX".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMASKZEROY = "crs:RetouchAreaMaskZeroY".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAMETHOD = "crs:RetouchAreaMethod".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAOFFSETY = "crs:RetouchAreaOffsetY".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREAOPACITY = "crs:RetouchAreaOpacity".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREASEED = "crs:RetouchAreaSeed".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREASOURCESTATE = "crs:RetouchAreaSourceState".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREASOURCEX = "crs:RetouchAreaSourceX".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHAREASPOTTYPE = "crs:RetouchAreaSpotType".hashCode();
    public static final int TAG_XMP_CRS_RETOUCHINFO = "crs:RetouchInfo".hashCode();
    public static final int TAG_XMP_CRS_SATURATION = "crs:Saturation".hashCode();
    public static final int TAG_XMP_CRS_SATURATIONADJUSTMENTAQUA = "crs:SaturationAdjustmentAqua".hashCode();
    public static final int TAG_XMP_CRS_SATURATIONADJUSTMENTBLUE = "crs:SaturationAdjustmentBlue".hashCode();
    public static final int TAG_XMP_CRS_SATURATIONADJUSTMENTGREEN = "crs:SaturationAdjustmentGreen".hashCode();
    public static final int TAG_XMP_CRS_SATURATIONADJUSTMENTMAGENTA = "crs:SaturationAdjustmentMagenta".hashCode();
    public static final int TAG_XMP_CRS_SATURATIONADJUSTMENTORANGE = "crs:SaturationAdjustmentOrange".hashCode();
    public static final int TAG_XMP_CRS_SATURATIONADJUSTMENTPURPLE = "crs:SaturationAdjustmentPurple".hashCode();
    public static final int TAG_XMP_CRS_SATURATIONADJUSTMENTRED = "crs:SaturationAdjustmentRed".hashCode();
    public static final int TAG_XMP_CRS_SATURATIONADJUSTMENTYELLOW = "crs:SaturationAdjustmentYellow".hashCode();
    public static final int TAG_XMP_CRS_SHADOWS = "crs:Shadows".hashCode();
    public static final int TAG_XMP_CRS_SHADOWS2012 = "crs:Shadows2012".hashCode();
    public static final int TAG_XMP_CRS_SHADOWTINT = "crs:ShadowTint".hashCode();
    public static final int TAG_XMP_CRS_SHARPENDETAIL = "crs:SharpenDetail".hashCode();
    public static final int TAG_XMP_CRS_SHARPENEDGEMASKING = "crs:SharpenEdgeMasking".hashCode();
    public static final int TAG_XMP_CRS_SHARPENRADIUS = "crs:SharpenRadius".hashCode();
    public static final int TAG_XMP_CRS_SHARPNESS = "crs:Sharpness".hashCode();
    public static final int TAG_XMP_CRS_SMOOTHNESS = "crs:Smoothness".hashCode();
    public static final int TAG_XMP_CRS_SPLITTONINGBALANCE = "crs:SplitToningBalance".hashCode();
    public static final int TAG_XMP_CRS_SPLITTONINGHIGHLIGHTHUE = "crs:SplitToningHighlightHue".hashCode();
    public static final int TAG_XMP_CRS_SPLITTONINGHIGHLIGHTSATURATION = "crs:SplitToningHighlightSaturation".hashCode();
    public static final int TAG_XMP_CRS_SPLITTONINGSHADOWHUE = "crs:SplitToningShadowHue".hashCode();
    public static final int TAG_XMP_CRS_SPLITTONINGSHADOWSATURATION = "crs:SplitToningShadowSaturation".hashCode();
    public static final int TAG_XMP_CRS_COLORTEMPERATURE = "crs:ColorTemperature".hashCode();
    public static final int TAG_XMP_CRS_TIFFHANDLING = "crs:TIFFHandling".hashCode();
    public static final int TAG_XMP_CRS_TINT = "crs:Tint".hashCode();
    public static final int TAG_XMP_CRS_TONECURVE = "crs:ToneCurve".hashCode();
    public static final int TAG_XMP_CRS_TONECURVEBLUE = "crs:ToneCurveBlue".hashCode();
    public static final int TAG_XMP_CRS_TONECURVEGREEN = "crs:ToneCurveGreen".hashCode();
    public static final int TAG_XMP_CRS_TONECURVENAME = "crs:ToneCurveName".hashCode();
    public static final int TAG_XMP_CRS_TONECURVENAME2012 = "crs:ToneCurveName2012".hashCode();
    public static final int TAG_XMP_CRS_TONECURVEPV2012 = "crs:ToneCurvePV2012".hashCode();
    public static final int TAG_XMP_CRS_TONECURVEPV2012BLUE = "crs:ToneCurvePV2012Blue".hashCode();
    public static final int TAG_XMP_CRS_TONECURVEPV2012GREEN = "crs:ToneCurvePV2012Green".hashCode();
    public static final int TAG_XMP_CRS_TONECURVEPV2012RED = "crs:ToneCurvePV2012Red".hashCode();
    public static final int TAG_XMP_CRS_TONECURVERED = "crs:ToneCurveRed".hashCode();
    public static final int TAG_XMP_CRS_TONEMAPSTRENGTH = "crs:ToneMapStrength".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTCENTERMODE = "crs:UprightCenterMode".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTCENTERNORMX = "crs:UprightCenterNormX".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTCENTERNORMY = "crs:UprightCenterNormY".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTDEPENDENTDIGEST = "crs:UprightDependentDigest".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTFOCALLENGTH35MM = "crs:UprightFocalLength35mm".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTFOCALMODE = "crs:UprightFocalMode".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTFOURSEGMENTSCOUNT = "crs:UprightFourSegmentsCount".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTPREVIEW = "crs:UprightPreview".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTTRANSFORM_0 = "crs:UprightTransform_0".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTTRANSFORM_1 = "crs:UprightTransform_1".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTTRANSFORM_2 = "crs:UprightTransform_2".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTTRANSFORM_3 = "crs:UprightTransform_3".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTTRANSFORM_4 = "crs:UprightTransform_4".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTTRANSFORM_5 = "crs:UprightTransform_5".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTTRANSFORMCOUNT = "crs:UprightTransformCount".hashCode();
    public static final int TAG_XMP_CRS_UPRIGHTVERSION = "crs:UprightVersion".hashCode();
    public static final int TAG_XMP_CRS_VERSION = "crs:Version".hashCode();
    public static final int TAG_XMP_CRS_VIBRANCE = "crs:Vibrance".hashCode();
    public static final int TAG_XMP_CRS_VIGNETTEAMOUNT = "crs:VignetteAmount".hashCode();
    public static final int TAG_XMP_CRS_VIGNETTEMIDPOINT = "crs:VignetteMidpoint".hashCode();
    public static final int TAG_XMP_CRS_WHAT = "crs:What".hashCode();
    public static final int TAG_XMP_CRS_WHITEBALANCE = "crs:WhiteBalance".hashCode();
    public static final int TAG_XMP_CRS_WHITES2012 = "crs:Whites2012".hashCode();

    // DC
    public static final int TAG_XMP_DC_CONTRIBUTOR = "dc:Contributor".hashCode();
    public static final int TAG_XMP_DC_COVERAGE = "dc:Coverage".hashCode();
    public static final int TAG_XMP_DC_CREATOR = "dc:Creator".hashCode();
    public static final int TAG_XMP_DC_DATE = "dc:Date".hashCode();
    public static final int TAG_XMP_DC_DESCRIPTION = "dc:Description".hashCode();
    public static final int TAG_XMP_DC_FORMAT = "dc:Format".hashCode();
    public static final int TAG_XMP_DC_IDENTIFIER = "dc:Identifier".hashCode();
    public static final int TAG_XMP_DC_LANGUAGE = "dc:Language".hashCode();
    public static final int TAG_XMP_DC_PUBLISHER = "dc:Publisher".hashCode();
    public static final int TAG_XMP_DC_RELATION = "dc:Relation".hashCode();
    public static final int TAG_XMP_DC_RIGHTS = "dc:Rights".hashCode();
    public static final int TAG_XMP_DC_SOURCE = "dc:Source".hashCode();
    public static final int TAG_XMP_DC_SUBJECT = "dc:Subject".hashCode();
    public static final int TAG_XMP_DC_TITLE = "dc:Title".hashCode();
    public static final int TAG_XMP_DC_TYPE = "dc:Type".hashCode();

    // DEX
    public static final int TAG_XMP_DEX_CRC32 = "dex:CRC32".hashCode();
    public static final int TAG_XMP_DEX_FFID = "dex:FFID".hashCode();
    public static final int TAG_XMP_DEX_LICENSETYPE = "dex:LicenseType".hashCode();
    public static final int TAG_XMP_DEX_OS = "dex:OS".hashCode();
    public static final int TAG_XMP_DEX_RATING = "dex:Rating".hashCode();
    public static final int TAG_XMP_DEX_REVISION = "dex:Revision".hashCode();
    public static final int TAG_XMP_DEX_SHORTDESCRIPTION = "dex:ShortDescription".hashCode();
    public static final int TAG_XMP_DEX_SOURCE = "dex:Source".hashCode();

    // DICOM
    public static final int TAG_XMP_DICOM_EQUIPMENTINSTITUTION = "DICOM:EquipmentInstitution".hashCode();
    public static final int TAG_XMP_DICOM_EQUIPMENTMANUFACTURER = "DICOM:EquipmentManufacturer".hashCode();
    public static final int TAG_XMP_DICOM_PATIENTBIRTHDATE = "DICOM:PatientBirthDate".hashCode();
    public static final int TAG_XMP_DICOM_PATIENTID = "DICOM:PatientID".hashCode();
    public static final int TAG_XMP_DICOM_PATIENTNAME = "DICOM:PatientName".hashCode();
    public static final int TAG_XMP_DICOM_PATIENTSEX = "DICOM:PatientSex".hashCode();
    public static final int TAG_XMP_DICOM_SERIESDATETIME = "DICOM:SeriesDateTime".hashCode();
    public static final int TAG_XMP_DICOM_SERIESDESCRIPTION = "DICOM:SeriesDescription".hashCode();
    public static final int TAG_XMP_DICOM_SERIESMODALITY = "DICOM:SeriesModality".hashCode();
    public static final int TAG_XMP_DICOM_SERIESNUMBER = "DICOM:SeriesNumber".hashCode();
    public static final int TAG_XMP_DICOM_STUDYDATETIME = "DICOM:StudyDateTime".hashCode();
    public static final int TAG_XMP_DICOM_STUDYDESCRIPTION = "DICOM:StudyDescription".hashCode();
    public static final int TAG_XMP_DICOM_STUDYID = "DICOM:StudyID".hashCode();
    public static final int TAG_XMP_DICOM_STUDYPHYSICIAN = "DICOM:StudyPhysician".hashCode();

    // DigiKam
    public static final int TAG_XMP_DIGIKAM_CAPTIONSAUTHORNAMES = "digiKam:CaptionsAuthorNames".hashCode();
    public static final int TAG_XMP_DIGIKAM_CAPTIONSDATETIMESTAMPS = "digiKam:CaptionsDateTimeStamps".hashCode();
    public static final int TAG_XMP_DIGIKAM_COLORLABEL = "digiKam:ColorLabel".hashCode();
    public static final int TAG_XMP_DIGIKAM_IMAGEHISTORY = "digiKam:ImageHistory".hashCode();
    public static final int TAG_XMP_DIGIKAM_IMAGEUNIQUEID = "digiKam:ImageUniqueID".hashCode();
    public static final int TAG_XMP_DIGIKAM_LENSCORRECTIONSETTINGS = "digiKam:LensCorrectionSettings".hashCode();
    public static final int TAG_XMP_DIGIKAM_PICKLABEL = "digiKam:PickLabel".hashCode();
    public static final int TAG_XMP_DIGIKAM_TAGSLIST = "digiKam:TagsList".hashCode();

    // EXIF
    public static final int TAG_XMP_EXIF_APERTUREVALUE = "exif:ApertureValue".hashCode();
    public static final int TAG_XMP_EXIF_BRIGHTNESSVALUE = "exif:BrightnessValue".hashCode();
    public static final int TAG_XMP_EXIF_CFAPATTERN = "exif:CFAPattern".hashCode();
    public static final int TAG_XMP_EXIF_CFAPATTERNCOLUMNS = "exif:CFAPatternColumns".hashCode();
    public static final int TAG_XMP_EXIF_CFAPATTERNROWS = "exif:CFAPatternRows".hashCode();
    public static final int TAG_XMP_EXIF_CFAPATTERNVALUES = "exif:CFAPatternValues".hashCode();
    public static final int TAG_XMP_EXIF_COLORSPACE = "exif:ColorSpace".hashCode();
    public static final int TAG_XMP_EXIF_COMPONENTSCONFIGURATION = "exif:ComponentsConfiguration".hashCode();
    public static final int TAG_XMP_EXIF_COMPRESSEDBITSPERPIXEL = "exif:CompressedBitsPerPixel".hashCode();
    public static final int TAG_XMP_EXIF_CONTRAST = "exif:Contrast".hashCode();
    public static final int TAG_XMP_EXIF_CUSTOMRENDERED = "exif:CustomRendered".hashCode();
    public static final int TAG_XMP_EXIF_DATETIMEDIGITIZED = "exif:DateTimeDigitized".hashCode();
    public static final int TAG_XMP_EXIF_DATETIMEORIGINAL = "exif:DateTimeOriginal".hashCode();
    public static final int TAG_XMP_EXIF_DEVICESETTINGDESCRIPTION = "exif:DeviceSettingDescription".hashCode();
    public static final int TAG_XMP_EXIF_DEVICESETTINGDESCRIPTIONCOLUMNS = "exif:DeviceSettingDescriptionColumns".hashCode();
    public static final int TAG_XMP_EXIF_DEVICESETTINGDESCRIPTIONROWS = "exif:DeviceSettingDescriptionRows".hashCode();
    public static final int TAG_XMP_EXIF_DEVICESETTINGDESCRIPTIONSETTINGS = "exif:DeviceSettingDescriptionSettings".hashCode();
    public static final int TAG_XMP_EXIF_DIGITALZOOMRATIO = "exif:DigitalZoomRatio".hashCode();
    public static final int TAG_XMP_EXIF_EXIFVERSION = "exif:ExifVersion".hashCode();
    public static final int TAG_XMP_EXIF_EXPOSURECOMPENSATION = "exif:ExposureCompensation".hashCode();
    public static final int TAG_XMP_EXIF_EXPOSUREINDEX = "exif:ExposureIndex".hashCode();
    public static final int TAG_XMP_EXIF_EXPOSUREMODE = "exif:ExposureMode".hashCode();
    public static final int TAG_XMP_EXIF_EXPOSUREPROGRAM = "exif:ExposureProgram".hashCode();
    public static final int TAG_XMP_EXIF_EXPOSURETIME = "exif:ExposureTime".hashCode();
    public static final int TAG_XMP_EXIF_FILESOURCE = "exif:FileSource".hashCode();
    public static final int TAG_XMP_EXIF_FLASH = "exif:Flash".hashCode();
    public static final int TAG_XMP_EXIF_FLASHENERGY = "exif:FlashEnergy".hashCode();
    public static final int TAG_XMP_EXIF_FLASHFIRED = "exif:FlashFired".hashCode();
    public static final int TAG_XMP_EXIF_FLASHFUNCTION = "exif:FlashFunction".hashCode();
    public static final int TAG_XMP_EXIF_FLASHMODE = "exif:FlashMode".hashCode();
    public static final int TAG_XMP_EXIF_FLASHPIXVERSION = "exif:FlashpixVersion".hashCode();
    public static final int TAG_XMP_EXIF_FLASHREDEYEMODE = "exif:FlashRedEyeMode".hashCode();
    public static final int TAG_XMP_EXIF_FLASHRETURN = "exif:FlashReturn".hashCode();
    public static final int TAG_XMP_EXIF_FNUMBER = "exif:FNumber".hashCode();
    public static final int TAG_XMP_EXIF_FOCALLENGTH = "exif:FocalLength".hashCode();
    public static final int TAG_XMP_EXIF_FOCALLENGTHIN35MMFORMAT = "exif:FocalLengthIn35mmFormat".hashCode();
    public static final int TAG_XMP_EXIF_FOCALPLANERESOLUTIONUNIT = "exif:FocalPlaneResolutionUnit".hashCode();
    public static final int TAG_XMP_EXIF_FOCALPLANEXRESOLUTION = "exif:FocalPlaneXResolution".hashCode();
    public static final int TAG_XMP_EXIF_FOCALPLANEYRESOLUTION = "exif:FocalPlaneYResolution".hashCode();
    public static final int TAG_XMP_EXIF_GAINCONTROL = "exif:GainControl".hashCode();
    public static final int TAG_XMP_EXIF_GPSALTITUDE = "exif:GPSAltitude".hashCode();
    public static final int TAG_XMP_EXIF_GPSALTITUDEREF = "exif:GPSAltitudeRef".hashCode();
    public static final int TAG_XMP_EXIF_GPSAREAINFORMATION = "exif:GPSAreaInformation".hashCode();
    public static final int TAG_XMP_EXIF_GPSDESTBEARING = "exif:GPSDestBearing".hashCode();
    public static final int TAG_XMP_EXIF_GPSDESTBEARINGREF = "exif:GPSDestBearingRef".hashCode();
    public static final int TAG_XMP_EXIF_GPSDESTDISTANCE = "exif:GPSDestDistance".hashCode();
    public static final int TAG_XMP_EXIF_GPSDESTDISTANCEREF = "exif:GPSDestDistanceRef".hashCode();
    public static final int TAG_XMP_EXIF_GPSDESTLATITUDE = "exif:GPSDestLatitude".hashCode();
    public static final int TAG_XMP_EXIF_GPSDESTLONGITUDE = "exif:GPSDestLongitude".hashCode();
    public static final int TAG_XMP_EXIF_GPSDIFFERENTIAL = "exif:GPSDifferential".hashCode();
    public static final int TAG_XMP_EXIF_GPSDOP = "exif:GPSDOP".hashCode();
    public static final int TAG_XMP_EXIF_GPSHPOSITIONINGERROR = "exif:GPSHPositioningError".hashCode();
    public static final int TAG_XMP_EXIF_GPSIMGDIRECTION = "exif:GPSImgDirection".hashCode();
    public static final int TAG_XMP_EXIF_GPSIMGDIRECTIONREF = "exif:GPSImgDirectionRef".hashCode();
    public static final int TAG_XMP_EXIF_GPSLATITUDE = "exif:GPSLatitude".hashCode();
    public static final int TAG_XMP_EXIF_GPSLONGITUDE = "exif:GPSLongitude".hashCode();
    public static final int TAG_XMP_EXIF_GPSMAPDATUM = "exif:GPSMapDatum".hashCode();
    public static final int TAG_XMP_EXIF_GPSMEASUREMODE = "exif:GPSMeasureMode".hashCode();
    public static final int TAG_XMP_EXIF_GPSPROCESSINGMETHOD = "exif:GPSProcessingMethod".hashCode();
    public static final int TAG_XMP_EXIF_GPSSATELLITES = "exif:GPSSatellites".hashCode();
    public static final int TAG_XMP_EXIF_GPSSPEED = "exif:GPSSpeed".hashCode();
    public static final int TAG_XMP_EXIF_GPSSPEEDREF = "exif:GPSSpeedRef".hashCode();
    public static final int TAG_XMP_EXIF_GPSSTATUS = "exif:GPSStatus".hashCode();
    public static final int TAG_XMP_EXIF_GPSDATETIME = "exif:GPSDateTime".hashCode();
    public static final int TAG_XMP_EXIF_GPSTRACK = "exif:GPSTrack".hashCode();
    public static final int TAG_XMP_EXIF_GPSTRACKREF = "exif:GPSTrackRef".hashCode();
    public static final int TAG_XMP_EXIF_GPSVERSIONID = "exif:GPSVersionID".hashCode();
    public static final int TAG_XMP_EXIF_IMAGEUNIQUEID = "exif:ImageUniqueID".hashCode();
    public static final int TAG_XMP_EXIF_ISO = "exif:ISO".hashCode();
    public static final int TAG_XMP_EXIF_LIGHTSOURCE = "exif:LightSource".hashCode();
    public static final int TAG_XMP_EXIF_MAKERNOTE = "exif:MakerNote".hashCode();
    public static final int TAG_XMP_EXIF_MAXAPERTUREVALUE = "exif:MaxApertureValue".hashCode();
    public static final int TAG_XMP_EXIF_METERINGMODE = "exif:MeteringMode".hashCode();
    public static final int TAG_XMP_EXIF_NATIVEDIGEST = "exif:NativeDigest".hashCode();
    public static final int TAG_XMP_EXIF_OPTO_ELECTRICCONVFACTOR = "exif:Opto-ElectricConvFactor".hashCode();
    public static final int TAG_XMP_EXIF_OECFCOLUMNS = "exif:OECFColumns".hashCode();
    public static final int TAG_XMP_EXIF_OECFNAMES = "exif:OECFNames".hashCode();
    public static final int TAG_XMP_EXIF_OECFROWS = "exif:OECFRows".hashCode();
    public static final int TAG_XMP_EXIF_OECFVALUES = "exif:OECFValues".hashCode();
    public static final int TAG_XMP_EXIF_EXIFIMAGEWIDTH = "exif:ExifImageWidth".hashCode();
    public static final int TAG_XMP_EXIF_EXIFIMAGEHEIGHT = "exif:ExifImageHeight".hashCode();
    public static final int TAG_XMP_EXIF_RELATEDSOUNDFILE = "exif:RelatedSoundFile".hashCode();
    public static final int TAG_XMP_EXIF_SATURATION = "exif:Saturation".hashCode();
    public static final int TAG_XMP_EXIF_SCENECAPTURETYPE = "exif:SceneCaptureType".hashCode();
    public static final int TAG_XMP_EXIF_SCENETYPE = "exif:SceneType".hashCode();
    public static final int TAG_XMP_EXIF_SENSINGMETHOD = "exif:SensingMethod".hashCode();
    public static final int TAG_XMP_EXIF_SHARPNESS = "exif:Sharpness".hashCode();
    public static final int TAG_XMP_EXIF_SHUTTERSPEEDVALUE = "exif:ShutterSpeedValue".hashCode();
    public static final int TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSE = "exif:SpatialFrequencyResponse".hashCode();
    public static final int TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSECOLUMNS = "exif:SpatialFrequencyResponseColumns".hashCode();
    public static final int TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSENAMES = "exif:SpatialFrequencyResponseNames".hashCode();
    public static final int TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSEROWS = "exif:SpatialFrequencyResponseRows".hashCode();
    public static final int TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSEVALUES = "exif:SpatialFrequencyResponseValues".hashCode();
    public static final int TAG_XMP_EXIF_SPECTRALSENSITIVITY = "exif:SpectralSensitivity".hashCode();
    public static final int TAG_XMP_EXIF_SUBJECTAREA = "exif:SubjectArea".hashCode();
    public static final int TAG_XMP_EXIF_SUBJECTDISTANCE = "exif:SubjectDistance".hashCode();
    public static final int TAG_XMP_EXIF_SUBJECTDISTANCERANGE = "exif:SubjectDistanceRange".hashCode();
    public static final int TAG_XMP_EXIF_SUBJECTLOCATION = "exif:SubjectLocation".hashCode();
    public static final int TAG_XMP_EXIF_USERCOMMENT = "exif:UserComment".hashCode();
    public static final int TAG_XMP_EXIF_WHITEBALANCE = "exif:WhiteBalance".hashCode();

    // EXIFEX
    public static final int TAG_XMP_EXIFEX_SERIALNUMBER = "exifEX:SerialNumber".hashCode();
    public static final int TAG_XMP_EXIFEX_OWNERNAME = "exifEX:OwnerName".hashCode();
    public static final int TAG_XMP_EXIFEX_GAMMA = "exifEX:Gamma".hashCode();
    public static final int TAG_XMP_EXIFEX_INTEROPINDEX = "exifEX:InteropIndex".hashCode();
    public static final int TAG_XMP_EXIFEX_ISOSPEED = "exifEX:ISOSpeed".hashCode();
    public static final int TAG_XMP_EXIFEX_ISOSPEEDLATITUDEYYY = "exifEX:ISOSpeedLatitudeyyy".hashCode();
    public static final int TAG_XMP_EXIFEX_ISOSPEEDLATITUDEZZZ = "exifEX:ISOSpeedLatitudezzz".hashCode();
    public static final int TAG_XMP_EXIFEX_LENSMAKE = "exifEX:LensMake".hashCode();
    public static final int TAG_XMP_EXIFEX_LENSMODEL = "exifEX:LensModel".hashCode();
    public static final int TAG_XMP_EXIFEX_LENSSERIALNUMBER = "exifEX:LensSerialNumber".hashCode();
    public static final int TAG_XMP_EXIFEX_LENSINFO = "exifEX:LensInfo".hashCode();
    public static final int TAG_XMP_EXIFEX_PHOTOGRAPHICSENSITIVITY = "exifEX:PhotographicSensitivity".hashCode();
    public static final int TAG_XMP_EXIFEX_RECOMMENDEDEXPOSUREINDEX = "exifEX:RecommendedExposureIndex".hashCode();
    public static final int TAG_XMP_EXIFEX_SENSITIVITYTYPE = "exifEX:SensitivityType".hashCode();
    public static final int TAG_XMP_EXIFEX_STANDARDOUTPUTSENSITIVITY = "exifEX:StandardOutputSensitivity".hashCode();

    // ExpressionMedia
    public static final int TAG_XMP_EXPRESSIONMEDIA_CATALOGSETS = "ExpressionMedia:CatalogSets".hashCode();
    public static final int TAG_XMP_EXPRESSIONMEDIA_EVENT = "ExpressionMedia:Event".hashCode();
    public static final int TAG_XMP_EXPRESSIONMEDIA_PEOPLE = "ExpressionMedia:People".hashCode();
    public static final int TAG_XMP_EXPRESSIONMEDIA_STATUS = "ExpressionMedia:Status".hashCode();

    // Extensis
    public static final int TAG_XMP_EXTENSIS_APPROVED = "extensis:Approved".hashCode();
    public static final int TAG_XMP_EXTENSIS_APPROVEDBY = "extensis:ApprovedBy".hashCode();
    public static final int TAG_XMP_EXTENSIS_CLIENTNAME = "extensis:ClientName".hashCode();
    public static final int TAG_XMP_EXTENSIS_JOBNAME = "extensis:JobName".hashCode();
    public static final int TAG_XMP_EXTENSIS_JOBSTATUS = "extensis:JobStatus".hashCode();
    public static final int TAG_XMP_EXTENSIS_ROUTEDTO = "extensis:RoutedTo".hashCode();
    public static final int TAG_XMP_EXTENSIS_ROUTINGNOTES = "extensis:RoutingNotes".hashCode();
    public static final int TAG_XMP_EXTENSIS_WORKTODO = "extensis:WorkToDo".hashCode();

    // FPV
    public static final int TAG_XMP_FPV_RICHTEXTCOMMENT = "fpv:RichTextComment".hashCode();

    // GAudio
    public static final int TAG_XMP_GAUDIO_AUDIODATA = "GAudio:AudioData".hashCode();
    public static final int TAG_XMP_GAUDIO_AUDIOMIMETYPE = "GAudio:AudioMimeType".hashCode();

    // GettyImages
    public static final int TAG_XMP_GETTYIMAGESGIFT_ASSETID = "GettyImagesGIFT:AssetID".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_CALLFORIMAGE = "GettyImagesGIFT:CallForImage".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_CAMERAFILENAME = "GettyImagesGIFT:CameraFilename".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_CAMERAMAKEMODEL = "GettyImagesGIFT:CameraMakeModel".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_CAMERASERIALNUMBER = "GettyImagesGIFT:CameraSerialNumber".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_COMPOSITION = "GettyImagesGIFT:Composition".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_EXCLUSIVECOVERAGE = "GettyImagesGIFT:ExclusiveCoverage".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_GIFTFTPPRIORITY = "GettyImagesGIFT:GIFTFtpPriority".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_IMAGERANK = "GettyImagesGIFT:ImageRank".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_MEDIAEVENTIDDATE = "GettyImagesGIFT:MediaEventIdDate".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_ORIGINALCREATEDATETIME = "GettyImagesGIFT:OriginalCreateDateTime".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_ORIGINALFILENAME = "GettyImagesGIFT:OriginalFileName".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_PARENTMEDIAEVENTID = "GettyImagesGIFT:ParentMediaEventID".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_PARENTMEID = "GettyImagesGIFT:ParentMEID".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_PERSONALITY = "GettyImagesGIFT:Personality".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_PRIMARYFTP = "GettyImagesGIFT:PrimaryFTP".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_ROUTINGDESTINATIONS = "GettyImagesGIFT:RoutingDestinations".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_ROUTINGEXCLUSIONS = "GettyImagesGIFT:RoutingExclusions".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_SECONDARYFTP = "GettyImagesGIFT:SecondaryFTP".hashCode();
    public static final int TAG_XMP_GETTYIMAGESGIFT_TIMESHOT = "GettyImagesGIFT:TimeShot".hashCode();

    // GImage
    public static final int TAG_XMP_GIMAGE_IMAGEDATA = "GImage:ImageData".hashCode();
    public static final int TAG_XMP_GIMAGE_IMAGEMIMETYPE = "GImage:ImageMimeType".hashCode();

    // GPano
    public static final int TAG_XMP_GPANO_CAPTURESOFTWARE = "GPano:CaptureSoftware".hashCode();
    public static final int TAG_XMP_GPANO_CROPPEDAREAIMAGEHEIGHTPIXELS = "GPano:CroppedAreaImageHeightPixels".hashCode();
    public static final int TAG_XMP_GPANO_CROPPEDAREAIMAGEWIDTHPIXELS = "GPano:CroppedAreaImageWidthPixels".hashCode();
    public static final int TAG_XMP_GPANO_CROPPEDAREALEFTPIXELS = "GPano:CroppedAreaLeftPixels".hashCode();
    public static final int TAG_XMP_GPANO_CROPPEDAREATOPPIXELS = "GPano:CroppedAreaTopPixels".hashCode();
    public static final int TAG_XMP_GPANO_EXPOSURELOCKUSED = "GPano:ExposureLockUsed".hashCode();
    public static final int TAG_XMP_GPANO_FIRSTPHOTODATE = "GPano:FirstPhotoDate".hashCode();
    public static final int TAG_XMP_GPANO_FULLPANOHEIGHTPIXELS = "GPano:FullPanoHeightPixels".hashCode();
    public static final int TAG_XMP_GPANO_FULLPANOWIDTHPIXELS = "GPano:FullPanoWidthPixels".hashCode();
    public static final int TAG_XMP_GPANO_INITIALCAMERADOLLY = "GPano:InitialCameraDolly".hashCode();
    public static final int TAG_XMP_GPANO_INITIALHORIZONTALFOVDEGREES = "GPano:InitialHorizontalFOVDegrees".hashCode();
    public static final int TAG_XMP_GPANO_INITIALVIEWHEADINGDEGREES = "GPano:InitialViewHeadingDegrees".hashCode();
    public static final int TAG_XMP_GPANO_INITIALVIEWPITCHDEGREES = "GPano:InitialViewPitchDegrees".hashCode();
    public static final int TAG_XMP_GPANO_INITIALVIEWROLLDEGREES = "GPano:InitialViewRollDegrees".hashCode();
    public static final int TAG_XMP_GPANO_LARGESTVALIDINTERIORRECTHEIGHT = "GPano:LargestValidInteriorRectHeight".hashCode();
    public static final int TAG_XMP_GPANO_LARGESTVALIDINTERIORRECTLEFT = "GPano:LargestValidInteriorRectLeft".hashCode();
    public static final int TAG_XMP_GPANO_LARGESTVALIDINTERIORRECTTOP = "GPano:LargestValidInteriorRectTop".hashCode();
    public static final int TAG_XMP_GPANO_LARGESTVALIDINTERIORRECTWIDTH = "GPano:LargestValidInteriorRectWidth".hashCode();
    public static final int TAG_XMP_GPANO_LASTPHOTODATE = "GPano:LastPhotoDate".hashCode();
    public static final int TAG_XMP_GPANO_POSEHEADINGDEGREES = "GPano:PoseHeadingDegrees".hashCode();
    public static final int TAG_XMP_GPANO_POSEPITCHDEGREES = "GPano:PosePitchDegrees".hashCode();
    public static final int TAG_XMP_GPANO_POSEROLLDEGREES = "GPano:PoseRollDegrees".hashCode();
    public static final int TAG_XMP_GPANO_PROJECTIONTYPE = "GPano:ProjectionType".hashCode();
    public static final int TAG_XMP_GPANO_SOURCEPHOTOSCOUNT = "GPano:SourcePhotosCount".hashCode();
    public static final int TAG_XMP_GPANO_STITCHINGSOFTWARE = "GPano:StitchingSoftware".hashCode();
    public static final int TAG_XMP_GPANO_USEPANORAMAVIEWER = "GPano:UsePanoramaViewer".hashCode();

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    protected static void addXmpTags(HashMap<Integer, String> map)
    {
        _tagNameMap.put(TAG_XMP_VALUE_COUNT, "XMP Value Count");

        // AAS
        _tagNameMap.put(TAG_XMP_AAS_AAS, "aas:aas");
        _tagNameMap.put(TAG_XMP_AAS_ACDSEE, "aas:acdsee");
        _tagNameMap.put(TAG_XMP_AAS_ALBUM, "aas:album");
        _tagNameMap.put(TAG_XMP_AAS_APPLE_FI, "aas:apple-fi");
        _tagNameMap.put(TAG_XMP_AAS_AUX, "aas:aux");
        _tagNameMap.put(TAG_XMP_AAS_CC, "aas:cc");
        _tagNameMap.put(TAG_XMP_AAS_CELL, "aas:cell");
        _tagNameMap.put(TAG_XMP_AAS_CREATORATOM, "aas:creatorAtom");
        _tagNameMap.put(TAG_XMP_AAS_CRS, "aas:crs");
        _tagNameMap.put(TAG_XMP_AAS_DC, "aas:dc");
        _tagNameMap.put(TAG_XMP_AAS_DEX, "aas:dex");
        _tagNameMap.put(TAG_XMP_AAS_DICOM, "aas:DICOM");
        _tagNameMap.put(TAG_XMP_AAS_DIGIKAM, "aas:digiKam");
        _tagNameMap.put(TAG_XMP_AAS_DWC, "aas:dwc");
        _tagNameMap.put(TAG_XMP_AAS_EXIF, "aas:exif");
        _tagNameMap.put(TAG_XMP_AAS_EXIFEX, "aas:exifEX");
        _tagNameMap.put(TAG_XMP_AAS_EXPRESSIONMEDIA, "aas:expressionmedia");
        _tagNameMap.put(TAG_XMP_AAS_EXTENSIS, "aas:extensis");
        _tagNameMap.put(TAG_XMP_AAS_FPV, "aas:fpv");
        _tagNameMap.put(TAG_XMP_AAS_GAUDIO, "aas:GAudio");
        _tagNameMap.put(TAG_XMP_AAS_GETTY, "aas:getty");
        _tagNameMap.put(TAG_XMP_AAS_GIMAGE, "aas:GImage");
        _tagNameMap.put(TAG_XMP_AAS_GPANO, "aas:GPano");
        _tagNameMap.put(TAG_XMP_AAS_GSPHERICAL, "aas:GSpherical");
        _tagNameMap.put(TAG_XMP_AAS_ICS, "aas:ics");
        _tagNameMap.put(TAG_XMP_AAS_IPTCCORE, "aas:iptcCore");
        _tagNameMap.put(TAG_XMP_AAS_IPTCEXT, "aas:iptcExt");
        _tagNameMap.put(TAG_XMP_AAS_LR, "aas:lr");
        _tagNameMap.put(TAG_XMP_AAS_MEDIAPRO, "aas:mediapro");
        _tagNameMap.put(TAG_XMP_AAS_MICROSOFT, "aas:microsoft");
        _tagNameMap.put(TAG_XMP_AAS_MP, "aas:MP");
        _tagNameMap.put(TAG_XMP_AAS_MP1, "aas:MP1");
        _tagNameMap.put(TAG_XMP_AAS_MWG_COLL, "aas:mwg-coll");
        _tagNameMap.put(TAG_XMP_AAS_MWG_KW, "aas:mwg-kw");
        _tagNameMap.put(TAG_XMP_AAS_MWG_RS, "aas:mwg-rs");
        _tagNameMap.put(TAG_XMP_AAS_PDF, "aas:pdf");
        _tagNameMap.put(TAG_XMP_AAS_PDFX, "aas:pdfx");
        _tagNameMap.put(TAG_XMP_AAS_PHOTOMECH, "aas:photomech");
        _tagNameMap.put(TAG_XMP_AAS_PHOTOSHOP, "aas:photoshop");
        _tagNameMap.put(TAG_XMP_AAS_PIXELLIVE, "aas:PixelLive");
        _tagNameMap.put(TAG_XMP_AAS_PLUS, "aas:plus");
        _tagNameMap.put(TAG_XMP_AAS_PMI, "aas:pmi");
        _tagNameMap.put(TAG_XMP_AAS_PRISM, "aas:prism");
        _tagNameMap.put(TAG_XMP_AAS_PRL, "aas:prl");
        _tagNameMap.put(TAG_XMP_AAS_PRM, "aas:prm");
        _tagNameMap.put(TAG_XMP_AAS_PUR, "aas:pur");
        _tagNameMap.put(TAG_XMP_AAS_RDF, "aas:rdf");
        _tagNameMap.put(TAG_XMP_AAS_SWF, "aas:swf");
        _tagNameMap.put(TAG_XMP_AAS_TIFF, "aas:tiff");
        _tagNameMap.put(TAG_XMP_AAS_X, "aas:x");
        _tagNameMap.put(TAG_XMP_AAS_XMP, "aas:xmp");
        _tagNameMap.put(TAG_XMP_AAS_XMPBJ, "aas:xmpBJ");
        _tagNameMap.put(TAG_XMP_AAS_XMPDM, "aas:xmpDM");
        _tagNameMap.put(TAG_XMP_AAS_XMPMM, "aas:xmpMM");
        _tagNameMap.put(TAG_XMP_AAS_XMPNOTE, "aas:xmpNote");
        _tagNameMap.put(TAG_XMP_AAS_XMPPLUS, "aas:xmpPLUS");
        _tagNameMap.put(TAG_XMP_AAS_XMPRIGHTS, "aas:xmpRights");
        _tagNameMap.put(TAG_XMP_AAS_XMPTPG, "aas:xmpTPg");

        //ACDSEE
        _tagNameMap.put(TAG_XMP_ACDSEE_AUTHOR, "acdsee:Author");
        _tagNameMap.put(TAG_XMP_ACDSEE_CAPTION, "acdsee:Caption");
        _tagNameMap.put(TAG_XMP_ACDSEE_CATEGORIES, "acdsee:Categories");
        _tagNameMap.put(TAG_XMP_ACDSEE_COLLECTIONS, "acdsee:Collections");
        _tagNameMap.put(TAG_XMP_ACDSEE_DATETIME, "acdsee:DateTime");
        _tagNameMap.put(TAG_XMP_ACDSEE_DPP, "acdsee:DPP");
        _tagNameMap.put(TAG_XMP_ACDSEE_EDITSTATUS, "acdsee:EditStatus");
        _tagNameMap.put(TAG_XMP_ACDSEE_FIXTUREIDENTIFIER, "acdsee:FixtureIdentifier");
        _tagNameMap.put(TAG_XMP_ACDSEE_KEYWORDS, "acdsee:Keywords");
        _tagNameMap.put(TAG_XMP_ACDSEE_NOTES, "acdsee:Notes");
        _tagNameMap.put(TAG_XMP_ACDSEE_OBJECTCYCLE, "acdsee:ObjectCycle");
        _tagNameMap.put(TAG_XMP_ACDSEE_ORIGINATINGPROGRAM, "acdsee:OriginatingProgram");
        _tagNameMap.put(TAG_XMP_ACDSEE_RATING, "acdsee:Rating");
        _tagNameMap.put(TAG_XMP_ACDSEE_RAWRPPUSED, "acdsee:Rawrppused");
        _tagNameMap.put(TAG_XMP_ACDSEE_RELEASEDATE, "acdsee:ReleaseDate");
        _tagNameMap.put(TAG_XMP_ACDSEE_RELEASETIME, "acdsee:ReleaseTime");
        _tagNameMap.put(TAG_XMP_ACDSEE_RPP, "acdsee:RPP");
        _tagNameMap.put(TAG_XMP_ACDSEE_SNAPSHOTS, "acdsee:Snapshots");
        _tagNameMap.put(TAG_XMP_ACDSEE_TAGGED, "acdsee:Tagged");

        // Album
        _tagNameMap.put(TAG_XMP_ALBUM_NOTES, "album:Notes");

        // Apple_fi
        _tagNameMap.put(TAG_XMP_APPLE_FI_ANGLEINFOROLL, "apple_fi:AngleInfoRoll");
        _tagNameMap.put(TAG_XMP_APPLE_FI_ANGLEINFOYAW, "apple_fi:AngleInfoYaw");
        _tagNameMap.put(TAG_XMP_APPLE_FI_CONFIDENCELEVEL, "apple_fi:ConfidenceLevel");
        _tagNameMap.put(TAG_XMP_APPLE_FI_FACEID, "apple_fi:FaceID");
        _tagNameMap.put(TAG_XMP_APPLE_FI_TIMESTAMP, "apple_fi:TimeStamp");

        // Aux
        _tagNameMap.put(TAG_XMP_AUX_APPROXIMATEFOCUSDISTANCE, "aux:ApproximateFocusDistance");
        _tagNameMap.put(TAG_XMP_AUX_DISTORTIONCORRECTIONALREADYAPPLIED, "aux:DistortionCorrectionAlreadyApplied");
        _tagNameMap.put(TAG_XMP_AUX_FIRMWARE, "aux:Firmware");
        _tagNameMap.put(TAG_XMP_AUX_FLASHCOMPENSATION, "aux:FlashCompensation");
        _tagNameMap.put(TAG_XMP_AUX_IMAGENUMBER, "aux:ImageNumber");
        _tagNameMap.put(TAG_XMP_AUX_ISMERGEDHDR, "aux:IsMergedHDR");
        _tagNameMap.put(TAG_XMP_AUX_ISMERGEDPANORAMA, "aux:IsMergedPanorama");
        _tagNameMap.put(TAG_XMP_AUX_LATERALCHROMATICABERRATIONCORRECTIONALREADYAPPLIED, "aux:LateralChromaticAberrationCorrectionAlreadyApplied");
        _tagNameMap.put(TAG_XMP_AUX_LENS, "aux:Lens");
        _tagNameMap.put(TAG_XMP_AUX_LENSDISTORTINFO, "aux:LensDistortInfo");
        _tagNameMap.put(TAG_XMP_AUX_LENSID, "aux:LensID");
        _tagNameMap.put(TAG_XMP_AUX_LENSINFO, "aux:LensInfo");
        _tagNameMap.put(TAG_XMP_AUX_LENSSERIALNUMBER, "aux:LensSerialNumber");
        _tagNameMap.put(TAG_XMP_AUX_OWNERNAME, "aux:OwnerName");
        _tagNameMap.put(TAG_XMP_AUX_SERIALNUMBER, "aux:SerialNumber");
        _tagNameMap.put(TAG_XMP_AUX_VIGNETTECORRECTIONALREADYAPPLIED, "aux:VignetteCorrectionAlreadyApplied");

        // CC
        _tagNameMap.put(TAG_XMP_CC_ATTRIBUTIONNAME, "cc:AttributionName");
        _tagNameMap.put(TAG_XMP_CC_ATTRIBUTIONURL, "cc:AttributionURL");
        _tagNameMap.put(TAG_XMP_CC_DEPRECATEDON, "cc:DeprecatedOn");
        _tagNameMap.put(TAG_XMP_CC_JURISDICTION, "cc:Jurisdiction");
        _tagNameMap.put(TAG_XMP_CC_LEGALCODE, "cc:LegalCode");
        _tagNameMap.put(TAG_XMP_CC_LICENSE, "cc:License");
        _tagNameMap.put(TAG_XMP_CC_MOREPERMISSIONS, "cc:MorePermissions");
        _tagNameMap.put(TAG_XMP_CC_PERMITS, "cc:Permits");
        _tagNameMap.put(TAG_XMP_CC_PROHIBITS, "cc:Prohibits");
        _tagNameMap.put(TAG_XMP_CC_REQUIRES, "cc:Requires");
        _tagNameMap.put(TAG_XMP_CC_USEGUIDELINES, "cc:UseGuidelines");

        // Cell
        _tagNameMap.put(TAG_XMP_CELL_CELLTOWERID, "cell:CellTowerID");
        _tagNameMap.put(TAG_XMP_CELL_CELLGLOBALID, "cell:CellGlobalID");
        _tagNameMap.put(TAG_XMP_CELL_LOCATIONAREACODE, "cell:LocationAreaCode");
        _tagNameMap.put(TAG_XMP_CELL_MOBILECOUNTRYCODE, "cell:MobileCountryCode");
        _tagNameMap.put(TAG_XMP_CELL_MOBILENETWORKCODE, "cell:MobileNetworkCode");
        _tagNameMap.put(TAG_XMP_CELL_CELLR, "cell:CellR");

        // CreatorAtom
        _tagNameMap.put(TAG_XMP_CREATORATOM_AEPROJECTLINK, "creatorAtom:AeProjectLink");
        _tagNameMap.put(TAG_XMP_CREATORATOM_AEPROJECTLINKCOMPOSITIONID, "creatorAtom:AeProjectLinkCompositionID");
        _tagNameMap.put(TAG_XMP_CREATORATOM_AEPROJECTLINKFULLPATH, "creatorAtom:AeProjectLinkFullPath");
        _tagNameMap.put(TAG_XMP_CREATORATOM_AEPROJECTLINKRENDEROUTPUTMODULEINDEX, "creatorAtom:AeProjectLinkRenderOutputModuleIndex");
        _tagNameMap.put(TAG_XMP_CREATORATOM_AEPROJECTLINKRENDERQUEUEITEMID, "creatorAtom:AeProjectLinkRenderQueueItemID");
        _tagNameMap.put(TAG_XMP_CREATORATOM_AEPROJECTLINKRENDERTIMESTAMP, "creatorAtom:AeProjectLinkRenderTimeStamp");
        _tagNameMap.put(TAG_XMP_CREATORATOM_MACATOM, "creatorAtom:MacAtom");
        _tagNameMap.put(TAG_XMP_CREATORATOM_MACATOMAPPLICATIONCODE, "creatorAtom:MacAtomApplicationCode");
        _tagNameMap.put(TAG_XMP_CREATORATOM_MACATOMINVOCATIONAPPLEEVENT, "creatorAtom:MacAtomInvocationAppleEvent");
        _tagNameMap.put(TAG_XMP_CREATORATOM_MACATOMPOSIXPROJECTPATH, "creatorAtom:MacAtomPosixProjectPath");
        _tagNameMap.put(TAG_XMP_CREATORATOM_WINDOWSATOM, "creatorAtom:WindowsAtom");
        _tagNameMap.put(TAG_XMP_CREATORATOM_WINDOWSATOMEXTENSION, "creatorAtom:WindowsAtomExtension");
        _tagNameMap.put(TAG_XMP_CREATORATOM_WINDOWSATOMINVOCATIONFLAGS, "creatorAtom:WindowsAtomInvocationFlags");
        _tagNameMap.put(TAG_XMP_CREATORATOM_WINDOWSATOMUNCPROJECTPATH, "creatorAtom:WindowsAtomUncProjectPath");

        // CRS
        _tagNameMap.put(TAG_XMP_CRS_ALREADYAPPLIED, "crs:AlreadyApplied");
        _tagNameMap.put(TAG_XMP_CRS_AUTOBRIGHTNESS, "crs:AutoBrightness");
        _tagNameMap.put(TAG_XMP_CRS_AUTOCONTRAST, "crs:AutoContrast");
        _tagNameMap.put(TAG_XMP_CRS_AUTOEXPOSURE, "crs:AutoExposure");
        _tagNameMap.put(TAG_XMP_CRS_AUTOLATERALCA, "crs:AutoLateralCA");
        _tagNameMap.put(TAG_XMP_CRS_AUTOSHADOWS, "crs:AutoShadows");
        _tagNameMap.put(TAG_XMP_CRS_AUTOWHITEVERSION, "crs:AutoWhiteVersion");
        _tagNameMap.put(TAG_XMP_CRS_BLACKS2012, "crs:Blacks2012");
        _tagNameMap.put(TAG_XMP_CRS_BLUEHUE, "crs:BlueHue");
        _tagNameMap.put(TAG_XMP_CRS_BLUESATURATION, "crs:BlueSaturation");
        _tagNameMap.put(TAG_XMP_CRS_BRIGHTNESS, "crs:Brightness");
        _tagNameMap.put(TAG_XMP_CRS_CAMERAPROFILE, "crs:CameraProfile");
        _tagNameMap.put(TAG_XMP_CRS_CAMERAPROFILEDIGEST, "crs:CameraProfileDigest");
        _tagNameMap.put(TAG_XMP_CRS_CHROMATICABERRATIONB, "crs:ChromaticAberrationB");
        _tagNameMap.put(TAG_XMP_CRS_CHROMATICABERRATIONR, "crs:ChromaticAberrationR");
        _tagNameMap.put(TAG_XMP_CRS_CIRCULARGRADIENTBASEDCORRECTIONS, "crs:CircularGradientBasedCorrections");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRACTIVE, "crs:CircGradBasedCorrActive");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRAMOUNT, "crs:CircGradBasedCorrAmount");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKS, "crs:CircGradBasedCorrMasks");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKALPHA, "crs:CircGradBasedCorrMaskAlpha");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKANGLE, "crs:CircGradBasedCorrMaskAngle");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKBOTTOM, "crs:CircGradBasedCorrMaskBottom");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKCENTERVALUE, "crs:CircGradBasedCorrMaskCenterValue");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKCENTERWEIGHT, "crs:CircGradBasedCorrMaskCenterWeight");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKDABS, "crs:CircGradBasedCorrMaskDabs");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFEATHER, "crs:CircGradBasedCorrMaskFeather");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFLIPPED, "crs:CircGradBasedCorrMaskFlipped");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFLOW, "crs:CircGradBasedCorrMaskFlow");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFULLX, "crs:CircGradBasedCorrMaskFullX");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKFULLY, "crs:CircGradBasedCorrMaskFullY");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKLEFT, "crs:CircGradBasedCorrMaskLeft");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKVALUE, "crs:CircGradBasedCorrMaskValue");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKMIDPOINT, "crs:CircGradBasedCorrMaskMidpoint");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKPERIMETERVALUE, "crs:CircGradBasedCorrMaskPerimeterValue");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKRADIUS, "crs:CircGradBasedCorrMaskRadius");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKRIGHT, "crs:CircGradBasedCorrMaskRight");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKROUNDNESS, "crs:CircGradBasedCorrMaskRoundness");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKSIZEX, "crs:CircGradBasedCorrMaskSizeX");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKSIZEY, "crs:CircGradBasedCorrMaskSizeY");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKTOP, "crs:CircGradBasedCorrMaskTop");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKVERSION, "crs:CircGradBasedCorrMaskVersion");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKWHAT, "crs:CircGradBasedCorrMaskWhat");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKX, "crs:CircGradBasedCorrMaskX");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKY, "crs:CircGradBasedCorrMaskY");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKZEROX, "crs:CircGradBasedCorrMaskZeroX");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMASKZEROY, "crs:CircGradBasedCorrMaskZeroY");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRBRIGHTNESS, "crs:CircGradBasedCorrBrightness");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRCLARITY, "crs:CircGradBasedCorrClarity");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRCLARITY2012, "crs:CircGradBasedCorrClarity2012");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRCONTRAST, "crs:CircGradBasedCorrContrast");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRCONTRAST2012, "crs:CircGradBasedCorrContrast2012");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRDEFRINGE, "crs:CircGradBasedCorrDefringe");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORREXPOSURE, "crs:CircGradBasedCorrExposure");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORREXPOSURE2012, "crs:CircGradBasedCorrExposure2012");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRHIGHLIGHTS2012, "crs:CircGradBasedCorrHighlights2012");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRLUMINANCENOISE, "crs:CircGradBasedCorrLuminanceNoise");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRMOIRE, "crs:CircGradBasedCorrMoire");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRSATURATION, "crs:CircGradBasedCorrSaturation");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRSHADOWS2012, "crs:CircGradBasedCorrShadows2012");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRSHARPNESS, "crs:CircGradBasedCorrSharpness");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRTEMPERATURE, "crs:CircGradBasedCorrTemperature");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRTINT, "crs:CircGradBasedCorrTint");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRHUE, "crs:CircGradBasedCorrHue");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRSATURATION, "crs:CircGradBasedCorrSaturation");
        _tagNameMap.put(TAG_XMP_CRS_CIRCGRADBASEDCORRWHAT, "crs:CircGradBasedCorrWhat");
        _tagNameMap.put(TAG_XMP_CRS_CLARITY, "crs:Clarity");
        _tagNameMap.put(TAG_XMP_CRS_CLARITY2012, "crs:Clarity2012");
        _tagNameMap.put(TAG_XMP_CRS_COLORNOISEREDUCTION, "crs:ColorNoiseReduction");
        _tagNameMap.put(TAG_XMP_CRS_COLORNOISEREDUCTIONDETAIL, "crs:ColorNoiseReductionDetail");
        _tagNameMap.put(TAG_XMP_CRS_COLORNOISEREDUCTIONSMOOTHNESS, "crs:ColorNoiseReductionSmoothness");
        _tagNameMap.put(TAG_XMP_CRS_CONTRAST, "crs:Contrast");
        _tagNameMap.put(TAG_XMP_CRS_CONTRAST2012, "crs:Contrast2012");
        _tagNameMap.put(TAG_XMP_CRS_CONVERTER, "crs:Converter");
        _tagNameMap.put(TAG_XMP_CRS_CONVERTTOGRAYSCALE, "crs:ConvertToGrayscale");
        _tagNameMap.put(TAG_XMP_CRS_CROPANGLE, "crs:CropAngle");
        _tagNameMap.put(TAG_XMP_CRS_CROPBOTTOM, "crs:CropBottom");
        _tagNameMap.put(TAG_XMP_CRS_CROPCONSTRAINTOWARP, "crs:CropConstrainToWarp");
        _tagNameMap.put(TAG_XMP_CRS_CROPHEIGHT, "crs:CropHeight");
        _tagNameMap.put(TAG_XMP_CRS_CROPLEFT, "crs:CropLeft");
        _tagNameMap.put(TAG_XMP_CRS_CROPRIGHT, "crs:CropRight");
        _tagNameMap.put(TAG_XMP_CRS_CROPTOP, "crs:CropTop");
        _tagNameMap.put(TAG_XMP_CRS_CROPUNIT, "crs:CropUnit");
        _tagNameMap.put(TAG_XMP_CRS_CROPUNITS, "crs:CropUnits");
        _tagNameMap.put(TAG_XMP_CRS_CROPWIDTH, "crs:CropWidth");
        _tagNameMap.put(TAG_XMP_CRS_DEFAULTAUTOGRAY, "crs:DefaultAutoGray");
        _tagNameMap.put(TAG_XMP_CRS_DEFAULTAUTOTONE, "crs:DefaultAutoTone");
        _tagNameMap.put(TAG_XMP_CRS_DEFAULTSSPECIFICTOISO, "crs:DefaultsSpecificToISO");
        _tagNameMap.put(TAG_XMP_CRS_DEFAULTSSPECIFICTOSERIAL, "crs:DefaultsSpecificToSerial");
        _tagNameMap.put(TAG_XMP_CRS_DEFRINGE, "crs:Defringe");
        _tagNameMap.put(TAG_XMP_CRS_DEFRINGEGREENAMOUNT, "crs:DefringeGreenAmount");
        _tagNameMap.put(TAG_XMP_CRS_DEFRINGEGREENHUEHI, "crs:DefringeGreenHueHi");
        _tagNameMap.put(TAG_XMP_CRS_DEFRINGEGREENHUELO, "crs:DefringeGreenHueLo");
        _tagNameMap.put(TAG_XMP_CRS_DEFRINGEPURPLEAMOUNT, "crs:DefringePurpleAmount");
        _tagNameMap.put(TAG_XMP_CRS_DEFRINGEPURPLEHUEHI, "crs:DefringePurpleHueHi");
        _tagNameMap.put(TAG_XMP_CRS_DEFRINGEPURPLEHUELO, "crs:DefringePurpleHueLo");
        _tagNameMap.put(TAG_XMP_CRS_DEHAZE, "crs:Dehaze");
        _tagNameMap.put(TAG_XMP_CRS_DNGIGNORESIDECARS, "crs:DNGIgnoreSidecars");
        _tagNameMap.put(TAG_XMP_CRS_EXPOSURE, "crs:Exposure");
        _tagNameMap.put(TAG_XMP_CRS_EXPOSURE2012, "crs:Exposure2012");
        _tagNameMap.put(TAG_XMP_CRS_FILLLIGHT, "crs:FillLight");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRECTIONS, "crs:GradientBasedCorrections");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRACTIVE, "crs:GradientBasedCorrActive");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRAMOUNT, "crs:GradientBasedCorrAmount");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKS, "crs:GradientBasedCorrMasks");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKALPHA, "crs:GradientBasedCorrMaskAlpha");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKANGLE, "crs:GradientBasedCorrMaskAngle");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKBOTTOM, "crs:GradientBasedCorrMaskBottom");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKCENTERVALUE, "crs:GradientBasedCorrMaskCenterValue");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKCENTERWEIGHT, "crs:GradientBasedCorrMaskCenterWeight");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKDABS, "crs:GradientBasedCorrMaskDabs");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKFEATHER, "crs:GradientBasedCorrMaskFeather");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKFLIPPED, "crs:GradientBasedCorrMaskFlipped");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKFLOW, "crs:GradientBasedCorrMaskFlow");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKFULLX, "crs:GradientBasedCorrMaskFullX");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKFULLY, "crs:GradientBasedCorrMaskFullY");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKLEFT, "crs:GradientBasedCorrMaskLeft");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKVALUE, "crs:GradientBasedCorrMaskValue");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKMIDPOINT, "crs:GradientBasedCorrMaskMidpoint");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKPERIMETERVALUE, "crs:GradientBasedCorrMaskPerimeterValue");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKRADIUS, "crs:GradientBasedCorrMaskRadius");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKRIGHT, "crs:GradientBasedCorrMaskRight");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKROUNDNESS, "crs:GradientBasedCorrMaskRoundness");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKSIZEX, "crs:GradientBasedCorrMaskSizeX");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKSIZEY, "crs:GradientBasedCorrMaskSizeY");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKTOP, "crs:GradientBasedCorrMaskTop");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKVERSION, "crs:GradientBasedCorrMaskVersion");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKWHAT, "crs:GradientBasedCorrMaskWhat");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKX, "crs:GradientBasedCorrMaskX");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKY, "crs:GradientBasedCorrMaskY");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKZEROX, "crs:GradientBasedCorrMaskZeroX");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMASKZEROY, "crs:GradientBasedCorrMaskZeroY");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRBRIGHTNESS, "crs:GradientBasedCorrBrightness");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRCLARITY, "crs:GradientBasedCorrClarity");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRCLARITY2012, "crs:GradientBasedCorrClarity2012");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRCONTRAST, "crs:GradientBasedCorrContrast");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRCONTRAST2012, "crs:GradientBasedCorrContrast2012");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRDEFRINGE, "crs:GradientBasedCorrDefringe");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORREXPOSURE, "crs:GradientBasedCorrExposure");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORREXPOSURE2012, "crs:GradientBasedCorrExposure2012");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRHIGHLIGHTS2012, "crs:GradientBasedCorrHighlights2012");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRLUMINANCENOISE, "crs:GradientBasedCorrLuminanceNoise");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRMOIRE, "crs:GradientBasedCorrMoire");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRSATURATION, "crs:GradientBasedCorrSaturation");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRSHADOWS2012, "crs:GradientBasedCorrShadows2012");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRSHARPNESS, "crs:GradientBasedCorrSharpness");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRTEMPERATURE, "crs:GradientBasedCorrTemperature");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRTINT, "crs:GradientBasedCorrTint");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRHUE, "crs:GradientBasedCorrHue");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRSATURATION, "crs:GradientBasedCorrSaturation");
        _tagNameMap.put(TAG_XMP_CRS_GRADIENTBASEDCORRWHAT, "crs:GradientBasedCorrWhat");
        _tagNameMap.put(TAG_XMP_CRS_GRAINAMOUNT, "crs:GrainAmount");
        _tagNameMap.put(TAG_XMP_CRS_GRAINFREQUENCY, "crs:GrainFrequency");
        _tagNameMap.put(TAG_XMP_CRS_GRAINSIZE, "crs:GrainSize");
        _tagNameMap.put(TAG_XMP_CRS_GRAYMIXERAQUA, "crs:GrayMixerAqua");
        _tagNameMap.put(TAG_XMP_CRS_GRAYMIXERBLUE, "crs:GrayMixerBlue");
        _tagNameMap.put(TAG_XMP_CRS_GRAYMIXERGREEN, "crs:GrayMixerGreen");
        _tagNameMap.put(TAG_XMP_CRS_GRAYMIXERMAGENTA, "crs:GrayMixerMagenta");
        _tagNameMap.put(TAG_XMP_CRS_GRAYMIXERORANGE, "crs:GrayMixerOrange");
        _tagNameMap.put(TAG_XMP_CRS_GRAYMIXERPURPLE, "crs:GrayMixerPurple");
        _tagNameMap.put(TAG_XMP_CRS_GRAYMIXERRED, "crs:GrayMixerRed");
        _tagNameMap.put(TAG_XMP_CRS_GRAYMIXERYELLOW, "crs:GrayMixerYellow");
        _tagNameMap.put(TAG_XMP_CRS_GREENHUE, "crs:GreenHue");
        _tagNameMap.put(TAG_XMP_CRS_GREENSATURATION, "crs:GreenSaturation");
        _tagNameMap.put(TAG_XMP_CRS_HASCROP, "crs:HasCrop");
        _tagNameMap.put(TAG_XMP_CRS_HASSETTINGS, "crs:HasSettings");
        _tagNameMap.put(TAG_XMP_CRS_HIGHLIGHTRECOVERY, "crs:HighlightRecovery");
        _tagNameMap.put(TAG_XMP_CRS_HIGHLIGHTS2012, "crs:Highlights2012");
        _tagNameMap.put(TAG_XMP_CRS_HUEADJUSTMENTAQUA, "crs:HueAdjustmentAqua");
        _tagNameMap.put(TAG_XMP_CRS_HUEADJUSTMENTBLUE, "crs:HueAdjustmentBlue");
        _tagNameMap.put(TAG_XMP_CRS_HUEADJUSTMENTGREEN, "crs:HueAdjustmentGreen");
        _tagNameMap.put(TAG_XMP_CRS_HUEADJUSTMENTMAGENTA, "crs:HueAdjustmentMagenta");
        _tagNameMap.put(TAG_XMP_CRS_HUEADJUSTMENTORANGE, "crs:HueAdjustmentOrange");
        _tagNameMap.put(TAG_XMP_CRS_HUEADJUSTMENTPURPLE, "crs:HueAdjustmentPurple");
        _tagNameMap.put(TAG_XMP_CRS_HUEADJUSTMENTRED, "crs:HueAdjustmentRed");
        _tagNameMap.put(TAG_XMP_CRS_HUEADJUSTMENTYELLOW, "crs:HueAdjustmentYellow");
        _tagNameMap.put(TAG_XMP_CRS_INCREMENTALTEMPERATURE, "crs:IncrementalTemperature");
        _tagNameMap.put(TAG_XMP_CRS_INCREMENTALTINT, "crs:IncrementalTint");
        _tagNameMap.put(TAG_XMP_CRS_JPEGHANDLING, "crs:JPEGHandling");
        _tagNameMap.put(TAG_XMP_CRS_LENSMANUALDISTORTIONAMOUNT, "crs:LensManualDistortionAmount");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILECHROMATICABERRATIONSCALE, "crs:LensProfileChromaticAberrationScale");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEDIGEST, "crs:LensProfileDigest");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEDISTORTIONSCALE, "crs:LensProfileDistortionScale");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEENABLE, "crs:LensProfileEnable");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEFILENAME, "crs:LensProfileFilename");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEMATCHKEYCAMERAMODELNAME, "crs:LensProfileMatchKeyCameraModelName");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEMATCHKEYEXIFMAKE, "crs:LensProfileMatchKeyExifMake");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEMATCHKEYEXIFMODEL, "crs:LensProfileMatchKeyExifModel");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEMATCHKEYISRAW, "crs:LensProfileMatchKeyIsRaw");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEMATCHKEYLENSID, "crs:LensProfileMatchKeyLensID");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEMATCHKEYLENSINFO, "crs:LensProfileMatchKeyLensInfo");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEMATCHKEYLENSNAME, "crs:LensProfileMatchKeyLensName");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEMATCHKEYSENSORFORMATFACTOR, "crs:LensProfileMatchKeySensorFormatFactor");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILENAME, "crs:LensProfileName");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILESETUP, "crs:LensProfileSetup");
        _tagNameMap.put(TAG_XMP_CRS_LENSPROFILEVIGNETTINGSCALE, "crs:LensProfileVignettingScale");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCEADJUSTMENTAQUA, "crs:LuminanceAdjustmentAqua");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCEADJUSTMENTBLUE, "crs:LuminanceAdjustmentBlue");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCEADJUSTMENTGREEN, "crs:LuminanceAdjustmentGreen");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCEADJUSTMENTMAGENTA, "crs:LuminanceAdjustmentMagenta");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCEADJUSTMENTORANGE, "crs:LuminanceAdjustmentOrange");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCEADJUSTMENTPURPLE, "crs:LuminanceAdjustmentPurple");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCEADJUSTMENTRED, "crs:LuminanceAdjustmentRed");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCEADJUSTMENTYELLOW, "crs:LuminanceAdjustmentYellow");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCENOISEREDUCTIONCONTRAST, "crs:LuminanceNoiseReductionContrast");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCENOISEREDUCTIONDETAIL, "crs:LuminanceNoiseReductionDetail");
        _tagNameMap.put(TAG_XMP_CRS_LUMINANCESMOOTHING, "crs:LuminanceSmoothing");
        _tagNameMap.put(TAG_XMP_CRS_MOIREFILTER, "crs:MoireFilter");
        _tagNameMap.put(TAG_XMP_CRS_NEGATIVECACHELARGEPREVIEWSIZE, "crs:NegativeCacheLargePreviewSize");
        _tagNameMap.put(TAG_XMP_CRS_NEGATIVECACHEMAXIMUMSIZE, "crs:NegativeCacheMaximumSize");
        _tagNameMap.put(TAG_XMP_CRS_NEGATIVECACHEPATH, "crs:NegativeCachePath");
        _tagNameMap.put(TAG_XMP_CRS_PAINTBASEDCORRECTIONS, "crs:PaintBasedCorrections");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONACTIVE, "crs:PaintCorrectionActive");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONAMOUNT, "crs:PaintCorrectionAmount");
        _tagNameMap.put(TAG_XMP_CRS_PAINTBASEDCORRECTIONMASKS, "crs:PaintBasedCorrectionMasks");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKALPHA, "crs:PaintCorrectionMaskAlpha");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKANGLE, "crs:PaintCorrectionMaskAngle");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKBOTTOM, "crs:PaintCorrectionMaskBottom");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKCENTERVALUE, "crs:PaintCorrectionMaskCenterValue");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKCENTERWEIGHT, "crs:PaintCorrectionMaskCenterWeight");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKDABS, "crs:PaintCorrectionMaskDabs");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKFEATHER, "crs:PaintCorrectionMaskFeather");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKFLIPPED, "crs:PaintCorrectionMaskFlipped");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKFLOW, "crs:PaintCorrectionMaskFlow");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKFULLX, "crs:PaintCorrectionMaskFullX");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKFULLY, "crs:PaintCorrectionMaskFullY");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKLEFT, "crs:PaintCorrectionMaskLeft");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKVALUE, "crs:PaintCorrectionMaskValue");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKMIDPOINT, "crs:PaintCorrectionMaskMidpoint");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKPERIMETERVALUE, "crs:PaintCorrectionMaskPerimeterValue");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKRADIUS, "crs:PaintCorrectionMaskRadius");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKRIGHT, "crs:PaintCorrectionMaskRight");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKROUNDNESS, "crs:PaintCorrectionMaskRoundness");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKSIZEX, "crs:PaintCorrectionMaskSizeX");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKSIZEY, "crs:PaintCorrectionMaskSizeY");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKTOP, "crs:PaintCorrectionMaskTop");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKVERSION, "crs:PaintCorrectionMaskVersion");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKWHAT, "crs:PaintCorrectionMaskWhat");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKX, "crs:PaintCorrectionMaskX");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKY, "crs:PaintCorrectionMaskY");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKZEROX, "crs:PaintCorrectionMaskZeroX");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMASKZEROY, "crs:PaintCorrectionMaskZeroY");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONBRIGHTNESS, "crs:PaintCorrectionBrightness");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONCLARITY, "crs:PaintCorrectionClarity");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONCLARITY2012, "crs:PaintCorrectionClarity2012");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONCONTRAST, "crs:PaintCorrectionContrast");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONCONTRAST2012, "crs:PaintCorrectionContrast2012");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONDEFRINGE, "crs:PaintCorrectionDefringe");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONEXPOSURE, "crs:PaintCorrectionExposure");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONEXPOSURE2012, "crs:PaintCorrectionExposure2012");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONHIGHLIGHTS2012, "crs:PaintCorrectionHighlights2012");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONLUMINANCENOISE, "crs:PaintCorrectionLuminanceNoise");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONMOIRE, "crs:PaintCorrectionMoire");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONSATURATION, "crs:PaintCorrectionSaturation");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONSHADOWS2012, "crs:PaintCorrectionShadows2012");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONSHARPNESS, "crs:PaintCorrectionSharpness");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONTEMPERATURE, "crs:PaintCorrectionTemperature");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONTINT, "crs:PaintCorrectionTint");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONHUE, "crs:PaintCorrectionHue");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONSATURATION, "crs:PaintCorrectionSaturation");
        _tagNameMap.put(TAG_XMP_CRS_PAINTCORRECTIONWHAT, "crs:PaintCorrectionWhat");
        _tagNameMap.put(TAG_XMP_CRS_PARAMETRICDARKS, "crs:ParametricDarks");
        _tagNameMap.put(TAG_XMP_CRS_PARAMETRICHIGHLIGHTS, "crs:ParametricHighlights");
        _tagNameMap.put(TAG_XMP_CRS_PARAMETRICHIGHLIGHTSPLIT, "crs:ParametricHighlightSplit");
        _tagNameMap.put(TAG_XMP_CRS_PARAMETRICLIGHTS, "crs:ParametricLights");
        _tagNameMap.put(TAG_XMP_CRS_PARAMETRICMIDTONESPLIT, "crs:ParametricMidtoneSplit");
        _tagNameMap.put(TAG_XMP_CRS_PARAMETRICSHADOWS, "crs:ParametricShadows");
        _tagNameMap.put(TAG_XMP_CRS_PARAMETRICSHADOWSPLIT, "crs:ParametricShadowSplit");
        _tagNameMap.put(TAG_XMP_CRS_PERSPECTIVEASPECT, "crs:PerspectiveAspect");
        _tagNameMap.put(TAG_XMP_CRS_PERSPECTIVEHORIZONTAL, "crs:PerspectiveHorizontal");
        _tagNameMap.put(TAG_XMP_CRS_PERSPECTIVEROTATE, "crs:PerspectiveRotate");
        _tagNameMap.put(TAG_XMP_CRS_PERSPECTIVESCALE, "crs:PerspectiveScale");
        _tagNameMap.put(TAG_XMP_CRS_PERSPECTIVEUPRIGHT, "crs:PerspectiveUpright");
        _tagNameMap.put(TAG_XMP_CRS_PERSPECTIVEVERTICAL, "crs:PerspectiveVertical");
        _tagNameMap.put(TAG_XMP_CRS_PERSPECTIVEX, "crs:PerspectiveX");
        _tagNameMap.put(TAG_XMP_CRS_PERSPECTIVEY, "crs:PerspectiveY");
        _tagNameMap.put(TAG_XMP_CRS_POSTCROPVIGNETTEAMOUNT, "crs:PostCropVignetteAmount");
        _tagNameMap.put(TAG_XMP_CRS_POSTCROPVIGNETTEFEATHER, "crs:PostCropVignetteFeather");
        _tagNameMap.put(TAG_XMP_CRS_POSTCROPVIGNETTEHIGHLIGHTCONTRAST, "crs:PostCropVignetteHighlightContrast");
        _tagNameMap.put(TAG_XMP_CRS_POSTCROPVIGNETTEMIDPOINT, "crs:PostCropVignetteMidpoint");
        _tagNameMap.put(TAG_XMP_CRS_POSTCROPVIGNETTEROUNDNESS, "crs:PostCropVignetteRoundness");
        _tagNameMap.put(TAG_XMP_CRS_POSTCROPVIGNETTESTYLE, "crs:PostCropVignetteStyle");
        _tagNameMap.put(TAG_XMP_CRS_PROCESSVERSION, "crs:ProcessVersion");
        _tagNameMap.put(TAG_XMP_CRS_RAWFILENAME, "crs:RawFileName");
        _tagNameMap.put(TAG_XMP_CRS_REDEYEINFO, "crs:RedEyeInfo");
        _tagNameMap.put(TAG_XMP_CRS_REDHUE, "crs:RedHue");
        _tagNameMap.put(TAG_XMP_CRS_REDSATURATION, "crs:RedSaturation");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAS, "crs:RetouchAreas");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAFEATHER, "crs:RetouchAreaFeather");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKS, "crs:RetouchAreaMasks");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKALPHA, "crs:RetouchAreaMaskAlpha");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKANGLE, "crs:RetouchAreaMaskAngle");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKBOTTOM, "crs:RetouchAreaMaskBottom");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKCENTERVALUE, "crs:RetouchAreaMaskCenterValue");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKCENTERWEIGHT, "crs:RetouchAreaMaskCenterWeight");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKDABS, "crs:RetouchAreaMaskDabs");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKFEATHER, "crs:RetouchAreaMaskFeather");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKFLIPPED, "crs:RetouchAreaMaskFlipped");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKFLOW, "crs:RetouchAreaMaskFlow");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKFULLX, "crs:RetouchAreaMaskFullX");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKFULLY, "crs:RetouchAreaMaskFullY");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKLEFT, "crs:RetouchAreaMaskLeft");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKVALUE, "crs:RetouchAreaMaskValue");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKMIDPOINT, "crs:RetouchAreaMaskMidpoint");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKPERIMETERVALUE, "crs:RetouchAreaMaskPerimeterValue");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKRADIUS, "crs:RetouchAreaMaskRadius");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKRIGHT, "crs:RetouchAreaMaskRight");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKROUNDNESS, "crs:RetouchAreaMaskRoundness");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKSIZEX, "crs:RetouchAreaMaskSizeX");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKSIZEY, "crs:RetouchAreaMaskSizeY");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKTOP, "crs:RetouchAreaMaskTop");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKVERSION, "crs:RetouchAreaMaskVersion");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKWHAT, "crs:RetouchAreaMaskWhat");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKX, "crs:RetouchAreaMaskX");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKY, "crs:RetouchAreaMaskY");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKZEROX, "crs:RetouchAreaMaskZeroX");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMASKZEROY, "crs:RetouchAreaMaskZeroY");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAMETHOD, "crs:RetouchAreaMethod");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAOFFSETY, "crs:RetouchAreaOffsetY");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREAOPACITY, "crs:RetouchAreaOpacity");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREASEED, "crs:RetouchAreaSeed");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREASOURCESTATE, "crs:RetouchAreaSourceState");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREASOURCEX, "crs:RetouchAreaSourceX");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHAREASPOTTYPE, "crs:RetouchAreaSpotType");
        _tagNameMap.put(TAG_XMP_CRS_RETOUCHINFO, "crs:RetouchInfo");
        _tagNameMap.put(TAG_XMP_CRS_SATURATION, "crs:Saturation");
        _tagNameMap.put(TAG_XMP_CRS_SATURATIONADJUSTMENTAQUA, "crs:SaturationAdjustmentAqua");
        _tagNameMap.put(TAG_XMP_CRS_SATURATIONADJUSTMENTBLUE, "crs:SaturationAdjustmentBlue");
        _tagNameMap.put(TAG_XMP_CRS_SATURATIONADJUSTMENTGREEN, "crs:SaturationAdjustmentGreen");
        _tagNameMap.put(TAG_XMP_CRS_SATURATIONADJUSTMENTMAGENTA, "crs:SaturationAdjustmentMagenta");
        _tagNameMap.put(TAG_XMP_CRS_SATURATIONADJUSTMENTORANGE, "crs:SaturationAdjustmentOrange");
        _tagNameMap.put(TAG_XMP_CRS_SATURATIONADJUSTMENTPURPLE, "crs:SaturationAdjustmentPurple");
        _tagNameMap.put(TAG_XMP_CRS_SATURATIONADJUSTMENTRED, "crs:SaturationAdjustmentRed");
        _tagNameMap.put(TAG_XMP_CRS_SATURATIONADJUSTMENTYELLOW, "crs:SaturationAdjustmentYellow");
        _tagNameMap.put(TAG_XMP_CRS_SHADOWS, "crs:Shadows");
        _tagNameMap.put(TAG_XMP_CRS_SHADOWS2012, "crs:Shadows2012");
        _tagNameMap.put(TAG_XMP_CRS_SHADOWTINT, "crs:ShadowTint");
        _tagNameMap.put(TAG_XMP_CRS_SHARPENDETAIL, "crs:SharpenDetail");
        _tagNameMap.put(TAG_XMP_CRS_SHARPENEDGEMASKING, "crs:SharpenEdgeMasking");
        _tagNameMap.put(TAG_XMP_CRS_SHARPENRADIUS, "crs:SharpenRadius");
        _tagNameMap.put(TAG_XMP_CRS_SHARPNESS, "crs:Sharpness");
        _tagNameMap.put(TAG_XMP_CRS_SMOOTHNESS, "crs:Smoothness");
        _tagNameMap.put(TAG_XMP_CRS_SPLITTONINGBALANCE, "crs:SplitToningBalance");
        _tagNameMap.put(TAG_XMP_CRS_SPLITTONINGHIGHLIGHTHUE, "crs:SplitToningHighlightHue");
        _tagNameMap.put(TAG_XMP_CRS_SPLITTONINGHIGHLIGHTSATURATION, "crs:SplitToningHighlightSaturation");
        _tagNameMap.put(TAG_XMP_CRS_SPLITTONINGSHADOWHUE, "crs:SplitToningShadowHue");
        _tagNameMap.put(TAG_XMP_CRS_SPLITTONINGSHADOWSATURATION, "crs:SplitToningShadowSaturation");
        _tagNameMap.put(TAG_XMP_CRS_COLORTEMPERATURE, "crs:ColorTemperature");
        _tagNameMap.put(TAG_XMP_CRS_TIFFHANDLING, "crs:TIFFHandling");
        _tagNameMap.put(TAG_XMP_CRS_TINT, "crs:Tint");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVE, "crs:ToneCurve");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVEBLUE, "crs:ToneCurveBlue");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVEGREEN, "crs:ToneCurveGreen");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVENAME, "crs:ToneCurveName");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVENAME2012, "crs:ToneCurveName2012");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVEPV2012, "crs:ToneCurvePV2012");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVEPV2012BLUE, "crs:ToneCurvePV2012Blue");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVEPV2012GREEN, "crs:ToneCurvePV2012Green");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVEPV2012RED, "crs:ToneCurvePV2012Red");
        _tagNameMap.put(TAG_XMP_CRS_TONECURVERED, "crs:ToneCurveRed");
        _tagNameMap.put(TAG_XMP_CRS_TONEMAPSTRENGTH, "crs:ToneMapStrength");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTCENTERMODE, "crs:UprightCenterMode");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTCENTERNORMX, "crs:UprightCenterNormX");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTCENTERNORMY, "crs:UprightCenterNormY");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTDEPENDENTDIGEST, "crs:UprightDependentDigest");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTFOCALLENGTH35MM, "crs:UprightFocalLength35mm");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTFOCALMODE, "crs:UprightFocalMode");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTFOURSEGMENTSCOUNT, "crs:UprightFourSegmentsCount");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTPREVIEW, "crs:UprightPreview");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTTRANSFORM_0, "crs:UprightTransform_0");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTTRANSFORM_1, "crs:UprightTransform_1");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTTRANSFORM_2, "crs:UprightTransform_2");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTTRANSFORM_3, "crs:UprightTransform_3");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTTRANSFORM_4, "crs:UprightTransform_4");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTTRANSFORM_5, "crs:UprightTransform_5");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTTRANSFORMCOUNT, "crs:UprightTransformCount");
        _tagNameMap.put(TAG_XMP_CRS_UPRIGHTVERSION, "crs:UprightVersion");
        _tagNameMap.put(TAG_XMP_CRS_VERSION, "crs:Version");
        _tagNameMap.put(TAG_XMP_CRS_VIBRANCE, "crs:Vibrance");
        _tagNameMap.put(TAG_XMP_CRS_VIGNETTEAMOUNT, "crs:VignetteAmount");
        _tagNameMap.put(TAG_XMP_CRS_VIGNETTEMIDPOINT, "crs:VignetteMidpoint");
        _tagNameMap.put(TAG_XMP_CRS_WHAT, "crs:What");
        _tagNameMap.put(TAG_XMP_CRS_WHITEBALANCE, "crs:WhiteBalance");
        _tagNameMap.put(TAG_XMP_CRS_WHITES2012, "crs:Whites2012");

        // DC
        _tagNameMap.put(TAG_XMP_DC_CONTRIBUTOR, "dc:Contributor");
        _tagNameMap.put(TAG_XMP_DC_COVERAGE, "dc:Coverage");
        _tagNameMap.put(TAG_XMP_DC_CREATOR, "dc:Creator");
        _tagNameMap.put(TAG_XMP_DC_DATE, "dc:Date");
        _tagNameMap.put(TAG_XMP_DC_DESCRIPTION, "dc:Description");
        _tagNameMap.put(TAG_XMP_DC_FORMAT, "dc:Format");
        _tagNameMap.put(TAG_XMP_DC_IDENTIFIER, "dc:Identifier");
        _tagNameMap.put(TAG_XMP_DC_LANGUAGE, "dc:Language");
        _tagNameMap.put(TAG_XMP_DC_PUBLISHER, "dc:Publisher");
        _tagNameMap.put(TAG_XMP_DC_RELATION, "dc:Relation");
        _tagNameMap.put(TAG_XMP_DC_RIGHTS, "dc:Rights");
        _tagNameMap.put(TAG_XMP_DC_SOURCE, "dc:Source");
        _tagNameMap.put(TAG_XMP_DC_SUBJECT, "dc:Subject");
        _tagNameMap.put(TAG_XMP_DC_TITLE, "dc:Title");
        _tagNameMap.put(TAG_XMP_DC_TYPE, "dc:Type");

        // DEX
        _tagNameMap.put(TAG_XMP_DEX_CRC32, "dex:CRC32");
        _tagNameMap.put(TAG_XMP_DEX_FFID, "dex:FFID");
        _tagNameMap.put(TAG_XMP_DEX_LICENSETYPE, "dex:LicenseType");
        _tagNameMap.put(TAG_XMP_DEX_OS, "dex:OS");
        _tagNameMap.put(TAG_XMP_DEX_RATING, "dex:Rating");
        _tagNameMap.put(TAG_XMP_DEX_REVISION, "dex:Revision");
        _tagNameMap.put(TAG_XMP_DEX_SHORTDESCRIPTION, "dex:ShortDescription");
        _tagNameMap.put(TAG_XMP_DEX_SOURCE, "dex:Source");

        // DICOM
        _tagNameMap.put(TAG_XMP_DICOM_EQUIPMENTINSTITUTION, "DICOM:EquipmentInstitution");
        _tagNameMap.put(TAG_XMP_DICOM_EQUIPMENTMANUFACTURER, "DICOM:EquipmentManufacturer");
        _tagNameMap.put(TAG_XMP_DICOM_PATIENTBIRTHDATE, "DICOM:PatientBirthDate");
        _tagNameMap.put(TAG_XMP_DICOM_PATIENTID, "DICOM:PatientID");
        _tagNameMap.put(TAG_XMP_DICOM_PATIENTNAME, "DICOM:PatientName");
        _tagNameMap.put(TAG_XMP_DICOM_PATIENTSEX, "DICOM:PatientSex");
        _tagNameMap.put(TAG_XMP_DICOM_SERIESDATETIME, "DICOM:SeriesDateTime");
        _tagNameMap.put(TAG_XMP_DICOM_SERIESDESCRIPTION, "DICOM:SeriesDescription");
        _tagNameMap.put(TAG_XMP_DICOM_SERIESMODALITY, "DICOM:SeriesModality");
        _tagNameMap.put(TAG_XMP_DICOM_SERIESNUMBER, "DICOM:SeriesNumber");
        _tagNameMap.put(TAG_XMP_DICOM_STUDYDATETIME, "DICOM:StudyDateTime");
        _tagNameMap.put(TAG_XMP_DICOM_STUDYDESCRIPTION, "DICOM:StudyDescription");
        _tagNameMap.put(TAG_XMP_DICOM_STUDYID, "DICOM:StudyID");
        _tagNameMap.put(TAG_XMP_DICOM_STUDYPHYSICIAN, "DICOM:StudyPhysician");

        // DigiKam
        _tagNameMap.put(TAG_XMP_DIGIKAM_CAPTIONSAUTHORNAMES, "digiKam:CaptionsAuthorNames");
        _tagNameMap.put(TAG_XMP_DIGIKAM_CAPTIONSDATETIMESTAMPS, "digiKam:CaptionsDateTimeStamps");
        _tagNameMap.put(TAG_XMP_DIGIKAM_COLORLABEL, "digiKam:ColorLabel");
        _tagNameMap.put(TAG_XMP_DIGIKAM_IMAGEHISTORY, "digiKam:ImageHistory");
        _tagNameMap.put(TAG_XMP_DIGIKAM_IMAGEUNIQUEID, "digiKam:ImageUniqueID");
        _tagNameMap.put(TAG_XMP_DIGIKAM_LENSCORRECTIONSETTINGS, "digiKam:LensCorrectionSettings");
        _tagNameMap.put(TAG_XMP_DIGIKAM_PICKLABEL, "digiKam:PickLabel");
        _tagNameMap.put(TAG_XMP_DIGIKAM_TAGSLIST, "digiKam:TagsList");

        // EXIF
        _tagNameMap.put(TAG_XMP_EXIF_APERTUREVALUE, "exif:ApertureValue");
        _tagNameMap.put(TAG_XMP_EXIF_BRIGHTNESSVALUE, "exif:BrightnessValue");
        _tagNameMap.put(TAG_XMP_EXIF_CFAPATTERN, "exif:CFAPattern");
        _tagNameMap.put(TAG_XMP_EXIF_CFAPATTERNCOLUMNS, "exif:CFAPatternColumns");
        _tagNameMap.put(TAG_XMP_EXIF_CFAPATTERNROWS, "exif:CFAPatternRows");
        _tagNameMap.put(TAG_XMP_EXIF_CFAPATTERNVALUES, "exif:CFAPatternValues");
        _tagNameMap.put(TAG_XMP_EXIF_COLORSPACE, "exif:ColorSpace");
        _tagNameMap.put(TAG_XMP_EXIF_COMPONENTSCONFIGURATION, "exif:ComponentsConfiguration");
        _tagNameMap.put(TAG_XMP_EXIF_COMPRESSEDBITSPERPIXEL, "exif:CompressedBitsPerPixel");
        _tagNameMap.put(TAG_XMP_EXIF_CONTRAST, "exif:Contrast");
        _tagNameMap.put(TAG_XMP_EXIF_CUSTOMRENDERED, "exif:CustomRendered");
        _tagNameMap.put(TAG_XMP_EXIF_DATETIMEDIGITIZED, "exif:DateTimeDigitized");
        _tagNameMap.put(TAG_XMP_EXIF_DATETIMEORIGINAL, "exif:DateTimeOriginal");
        _tagNameMap.put(TAG_XMP_EXIF_DEVICESETTINGDESCRIPTION, "exif:DeviceSettingDescription");
        _tagNameMap.put(TAG_XMP_EXIF_DEVICESETTINGDESCRIPTIONCOLUMNS, "exif:DeviceSettingDescriptionColumns");
        _tagNameMap.put(TAG_XMP_EXIF_DEVICESETTINGDESCRIPTIONROWS, "exif:DeviceSettingDescriptionRows");
        _tagNameMap.put(TAG_XMP_EXIF_DEVICESETTINGDESCRIPTIONSETTINGS, "exif:DeviceSettingDescriptionSettings");
        _tagNameMap.put(TAG_XMP_EXIF_DIGITALZOOMRATIO, "exif:DigitalZoomRatio");
        _tagNameMap.put(TAG_XMP_EXIF_EXIFVERSION, "exif:ExifVersion");
        _tagNameMap.put(TAG_XMP_EXIF_EXPOSURECOMPENSATION, "exif:ExposureCompensation");
        _tagNameMap.put(TAG_XMP_EXIF_EXPOSUREINDEX, "exif:ExposureIndex");
        _tagNameMap.put(TAG_XMP_EXIF_EXPOSUREMODE, "exif:ExposureMode");
        _tagNameMap.put(TAG_XMP_EXIF_EXPOSUREPROGRAM, "exif:ExposureProgram");
        _tagNameMap.put(TAG_XMP_EXIF_EXPOSURETIME, "exif:ExposureTime");
        _tagNameMap.put(TAG_XMP_EXIF_FILESOURCE, "exif:FileSource");
        _tagNameMap.put(TAG_XMP_EXIF_FLASH, "exif:Flash");
        _tagNameMap.put(TAG_XMP_EXIF_FLASHENERGY, "exif:FlashEnergy");
        _tagNameMap.put(TAG_XMP_EXIF_FLASHFIRED, "exif:FlashFired");
        _tagNameMap.put(TAG_XMP_EXIF_FLASHFUNCTION, "exif:FlashFunction");
        _tagNameMap.put(TAG_XMP_EXIF_FLASHMODE, "exif:FlashMode");
        _tagNameMap.put(TAG_XMP_EXIF_FLASHPIXVERSION, "exif:FlashpixVersion");
        _tagNameMap.put(TAG_XMP_EXIF_FLASHREDEYEMODE, "exif:FlashRedEyeMode");
        _tagNameMap.put(TAG_XMP_EXIF_FLASHRETURN, "exif:FlashReturn");
        _tagNameMap.put(TAG_XMP_EXIF_FNUMBER, "exif:FNumber");
        _tagNameMap.put(TAG_XMP_EXIF_FOCALLENGTH, "exif:FocalLength");
        _tagNameMap.put(TAG_XMP_EXIF_FOCALLENGTHIN35MMFORMAT, "exif:FocalLengthIn35mmFormat");
        _tagNameMap.put(TAG_XMP_EXIF_FOCALPLANERESOLUTIONUNIT, "exif:FocalPlaneResolutionUnit");
        _tagNameMap.put(TAG_XMP_EXIF_FOCALPLANEXRESOLUTION, "exif:FocalPlaneXResolution");
        _tagNameMap.put(TAG_XMP_EXIF_FOCALPLANEYRESOLUTION, "exif:FocalPlaneYResolution");
        _tagNameMap.put(TAG_XMP_EXIF_GAINCONTROL, "exif:GainControl");
        _tagNameMap.put(TAG_XMP_EXIF_GPSALTITUDE, "exif:GPSAltitude");
        _tagNameMap.put(TAG_XMP_EXIF_GPSALTITUDEREF, "exif:GPSAltitudeRef");
        _tagNameMap.put(TAG_XMP_EXIF_GPSAREAINFORMATION, "exif:GPSAreaInformation");
        _tagNameMap.put(TAG_XMP_EXIF_GPSDESTBEARING, "exif:GPSDestBearing");
        _tagNameMap.put(TAG_XMP_EXIF_GPSDESTBEARINGREF, "exif:GPSDestBearingRef");
        _tagNameMap.put(TAG_XMP_EXIF_GPSDESTDISTANCE, "exif:GPSDestDistance");
        _tagNameMap.put(TAG_XMP_EXIF_GPSDESTDISTANCEREF, "exif:GPSDestDistanceRef");
        _tagNameMap.put(TAG_XMP_EXIF_GPSDESTLATITUDE, "exif:GPSDestLatitude");
        _tagNameMap.put(TAG_XMP_EXIF_GPSDESTLONGITUDE, "exif:GPSDestLongitude");
        _tagNameMap.put(TAG_XMP_EXIF_GPSDIFFERENTIAL, "exif:GPSDifferential");
        _tagNameMap.put(TAG_XMP_EXIF_GPSDOP, "exif:GPSDOP");
        _tagNameMap.put(TAG_XMP_EXIF_GPSHPOSITIONINGERROR, "exif:GPSHPositioningError");
        _tagNameMap.put(TAG_XMP_EXIF_GPSIMGDIRECTION, "exif:GPSImgDirection");
        _tagNameMap.put(TAG_XMP_EXIF_GPSIMGDIRECTIONREF, "exif:GPSImgDirectionRef");
        _tagNameMap.put(TAG_XMP_EXIF_GPSLATITUDE, "exif:GPSLatitude");
        _tagNameMap.put(TAG_XMP_EXIF_GPSLONGITUDE, "exif:GPSLongitude");
        _tagNameMap.put(TAG_XMP_EXIF_GPSMAPDATUM, "exif:GPSMapDatum");
        _tagNameMap.put(TAG_XMP_EXIF_GPSMEASUREMODE, "exif:GPSMeasureMode");
        _tagNameMap.put(TAG_XMP_EXIF_GPSPROCESSINGMETHOD, "exif:GPSProcessingMethod");
        _tagNameMap.put(TAG_XMP_EXIF_GPSSATELLITES, "exif:GPSSatellites");
        _tagNameMap.put(TAG_XMP_EXIF_GPSSPEED, "exif:GPSSpeed");
        _tagNameMap.put(TAG_XMP_EXIF_GPSSPEEDREF, "exif:GPSSpeedRef");
        _tagNameMap.put(TAG_XMP_EXIF_GPSSTATUS, "exif:GPSStatus");
        _tagNameMap.put(TAG_XMP_EXIF_GPSDATETIME, "exif:GPSDateTime");
        _tagNameMap.put(TAG_XMP_EXIF_GPSTRACK, "exif:GPSTrack");
        _tagNameMap.put(TAG_XMP_EXIF_GPSTRACKREF, "exif:GPSTrackRef");
        _tagNameMap.put(TAG_XMP_EXIF_GPSVERSIONID, "exif:GPSVersionID");
        _tagNameMap.put(TAG_XMP_EXIF_IMAGEUNIQUEID, "exif:ImageUniqueID");
        _tagNameMap.put(TAG_XMP_EXIF_ISO, "exif:ISO");
        _tagNameMap.put(TAG_XMP_EXIF_LIGHTSOURCE, "exif:LightSource");
        _tagNameMap.put(TAG_XMP_EXIF_MAKERNOTE, "exif:MakerNote");
        _tagNameMap.put(TAG_XMP_EXIF_MAXAPERTUREVALUE, "exif:MaxApertureValue");
        _tagNameMap.put(TAG_XMP_EXIF_METERINGMODE, "exif:MeteringMode");
        _tagNameMap.put(TAG_XMP_EXIF_NATIVEDIGEST, "exif:NativeDigest");
        _tagNameMap.put(TAG_XMP_EXIF_OPTO_ELECTRICCONVFACTOR, "exif:Opto-ElectricConvFactor");
        _tagNameMap.put(TAG_XMP_EXIF_OECFCOLUMNS, "exif:OECFColumns");
        _tagNameMap.put(TAG_XMP_EXIF_OECFNAMES, "exif:OECFNames");
        _tagNameMap.put(TAG_XMP_EXIF_OECFROWS, "exif:OECFRows");
        _tagNameMap.put(TAG_XMP_EXIF_OECFVALUES, "exif:OECFValues");
        _tagNameMap.put(TAG_XMP_EXIF_EXIFIMAGEWIDTH, "exif:ExifImageWidth");
        _tagNameMap.put(TAG_XMP_EXIF_EXIFIMAGEHEIGHT, "exif:ExifImageHeight");
        _tagNameMap.put(TAG_XMP_EXIF_RELATEDSOUNDFILE, "exif:RelatedSoundFile");
        _tagNameMap.put(TAG_XMP_EXIF_SATURATION, "exif:Saturation");
        _tagNameMap.put(TAG_XMP_EXIF_SCENECAPTURETYPE, "exif:SceneCaptureType");
        _tagNameMap.put(TAG_XMP_EXIF_SCENETYPE, "exif:SceneType");
        _tagNameMap.put(TAG_XMP_EXIF_SENSINGMETHOD, "exif:SensingMethod");
        _tagNameMap.put(TAG_XMP_EXIF_SHARPNESS, "exif:Sharpness");
        _tagNameMap.put(TAG_XMP_EXIF_SHUTTERSPEEDVALUE, "exif:ShutterSpeedValue");
        _tagNameMap.put(TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSE, "exif:SpatialFrequencyResponse");
        _tagNameMap.put(TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSECOLUMNS, "exif:SpatialFrequencyResponseColumns");
        _tagNameMap.put(TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSENAMES, "exif:SpatialFrequencyResponseNames");
        _tagNameMap.put(TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSEROWS, "exif:SpatialFrequencyResponseRows");
        _tagNameMap.put(TAG_XMP_EXIF_SPATIALFREQUENCYRESPONSEVALUES, "exif:SpatialFrequencyResponseValues");
        _tagNameMap.put(TAG_XMP_EXIF_SPECTRALSENSITIVITY, "exif:SpectralSensitivity");
        _tagNameMap.put(TAG_XMP_EXIF_SUBJECTAREA, "exif:SubjectArea");
        _tagNameMap.put(TAG_XMP_EXIF_SUBJECTDISTANCE, "exif:SubjectDistance");
        _tagNameMap.put(TAG_XMP_EXIF_SUBJECTDISTANCERANGE, "exif:SubjectDistanceRange");
        _tagNameMap.put(TAG_XMP_EXIF_SUBJECTLOCATION, "exif:SubjectLocation");
        _tagNameMap.put(TAG_XMP_EXIF_USERCOMMENT, "exif:UserComment");
        _tagNameMap.put(TAG_XMP_EXIF_WHITEBALANCE, "exif:WhiteBalance");

        // EXIFEX
        _tagNameMap.put(TAG_XMP_EXIFEX_SERIALNUMBER, "exifEX:SerialNumber");
        _tagNameMap.put(TAG_XMP_EXIFEX_OWNERNAME, "exifEX:OwnerName");
        _tagNameMap.put(TAG_XMP_EXIFEX_GAMMA, "exifEX:Gamma");
        _tagNameMap.put(TAG_XMP_EXIFEX_INTEROPINDEX, "exifEX:InteropIndex");
        _tagNameMap.put(TAG_XMP_EXIFEX_ISOSPEED, "exifEX:ISOSpeed");
        _tagNameMap.put(TAG_XMP_EXIFEX_ISOSPEEDLATITUDEYYY, "exifEX:ISOSpeedLatitudeyyy");
        _tagNameMap.put(TAG_XMP_EXIFEX_ISOSPEEDLATITUDEZZZ, "exifEX:ISOSpeedLatitudezzz");
        _tagNameMap.put(TAG_XMP_EXIFEX_LENSMAKE, "exifEX:LensMake");
        _tagNameMap.put(TAG_XMP_EXIFEX_LENSMODEL, "exifEX:LensModel");
        _tagNameMap.put(TAG_XMP_EXIFEX_LENSSERIALNUMBER, "exifEX:LensSerialNumber");
        _tagNameMap.put(TAG_XMP_EXIFEX_LENSINFO, "exifEX:LensInfo");
        _tagNameMap.put(TAG_XMP_EXIFEX_PHOTOGRAPHICSENSITIVITY, "exifEX:PhotographicSensitivity");
        _tagNameMap.put(TAG_XMP_EXIFEX_RECOMMENDEDEXPOSUREINDEX, "exifEX:RecommendedExposureIndex");
        _tagNameMap.put(TAG_XMP_EXIFEX_SENSITIVITYTYPE, "exifEX:SensitivityType");
        _tagNameMap.put(TAG_XMP_EXIFEX_STANDARDOUTPUTSENSITIVITY, "exifEX:StandardOutputSensitivity");

        // ExpressionMedia
        _tagNameMap.put(TAG_XMP_EXPRESSIONMEDIA_CATALOGSETS, "ExpressionMedia:CatalogSets");
        _tagNameMap.put(TAG_XMP_EXPRESSIONMEDIA_EVENT, "ExpressionMedia:Event");
        _tagNameMap.put(TAG_XMP_EXPRESSIONMEDIA_PEOPLE, "ExpressionMedia:People");
        _tagNameMap.put(TAG_XMP_EXPRESSIONMEDIA_STATUS, "ExpressionMedia:Status");

        // Extensis
        _tagNameMap.put(TAG_XMP_EXTENSIS_APPROVED, "extensis:Approved");
        _tagNameMap.put(TAG_XMP_EXTENSIS_APPROVEDBY, "extensis:ApprovedBy");
        _tagNameMap.put(TAG_XMP_EXTENSIS_CLIENTNAME, "extensis:ClientName");
        _tagNameMap.put(TAG_XMP_EXTENSIS_JOBNAME, "extensis:JobName");
        _tagNameMap.put(TAG_XMP_EXTENSIS_JOBSTATUS, "extensis:JobStatus");
        _tagNameMap.put(TAG_XMP_EXTENSIS_ROUTEDTO, "extensis:RoutedTo");
        _tagNameMap.put(TAG_XMP_EXTENSIS_ROUTINGNOTES, "extensis:RoutingNotes");
        _tagNameMap.put(TAG_XMP_EXTENSIS_WORKTODO, "extensis:WorkToDo");

        // FPV
        _tagNameMap.put(TAG_XMP_FPV_RICHTEXTCOMMENT, "fpv:RichTextComment");

        // GAudio
        _tagNameMap.put(TAG_XMP_GAUDIO_AUDIODATA, "GAudio:AudioData");
        _tagNameMap.put(TAG_XMP_GAUDIO_AUDIOMIMETYPE, "GAudio:AudioMimeType");

        // GettyImages
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_ASSETID, "GettyImagesGIFT:AssetID");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_CALLFORIMAGE, "GettyImagesGIFT:CallForImage");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_CAMERAFILENAME, "GettyImagesGIFT:CameraFilename");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_CAMERAMAKEMODEL, "GettyImagesGIFT:CameraMakeModel");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_CAMERASERIALNUMBER, "GettyImagesGIFT:CameraSerialNumber");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_COMPOSITION, "GettyImagesGIFT:Composition");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_EXCLUSIVECOVERAGE, "GettyImagesGIFT:ExclusiveCoverage");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_GIFTFTPPRIORITY, "GettyImagesGIFT:GIFTFtpPriority");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_IMAGERANK, "GettyImagesGIFT:ImageRank");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_MEDIAEVENTIDDATE, "GettyImagesGIFT:MediaEventIdDate");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_ORIGINALCREATEDATETIME, "GettyImagesGIFT:OriginalCreateDateTime");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_ORIGINALFILENAME, "GettyImagesGIFT:OriginalFileName");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_PARENTMEDIAEVENTID, "GettyImagesGIFT:ParentMediaEventID");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_PARENTMEID, "GettyImagesGIFT:ParentMEID");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_PERSONALITY, "GettyImagesGIFT:Personality");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_PRIMARYFTP, "GettyImagesGIFT:PrimaryFTP");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_ROUTINGDESTINATIONS, "GettyImagesGIFT:RoutingDestinations");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_ROUTINGEXCLUSIONS, "GettyImagesGIFT:RoutingExclusions");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_SECONDARYFTP, "GettyImagesGIFT:SecondaryFTP");
        _tagNameMap.put(TAG_XMP_GETTYIMAGESGIFT_TIMESHOT, "GettyImagesGIFT:TimeShot");

        // GImage
        _tagNameMap.put(TAG_XMP_GIMAGE_IMAGEDATA, "GImage:ImageData");
        _tagNameMap.put(TAG_XMP_GIMAGE_IMAGEMIMETYPE, "GImage:ImageMimeType");

        // GPano
        _tagNameMap.put(TAG_XMP_GPANO_CAPTURESOFTWARE, "GPano:CaptureSoftware");
        _tagNameMap.put(TAG_XMP_GPANO_CROPPEDAREAIMAGEHEIGHTPIXELS, "GPano:CroppedAreaImageHeightPixels");
        _tagNameMap.put(TAG_XMP_GPANO_CROPPEDAREAIMAGEWIDTHPIXELS, "GPano:CroppedAreaImageWidthPixels");
        _tagNameMap.put(TAG_XMP_GPANO_CROPPEDAREALEFTPIXELS, "GPano:CroppedAreaLeftPixels");
        _tagNameMap.put(TAG_XMP_GPANO_CROPPEDAREATOPPIXELS, "GPano:CroppedAreaTopPixels");
        _tagNameMap.put(TAG_XMP_GPANO_EXPOSURELOCKUSED, "GPano:ExposureLockUsed");
        _tagNameMap.put(TAG_XMP_GPANO_FIRSTPHOTODATE, "GPano:FirstPhotoDate");
        _tagNameMap.put(TAG_XMP_GPANO_FULLPANOHEIGHTPIXELS, "GPano:FullPanoHeightPixels");
        _tagNameMap.put(TAG_XMP_GPANO_FULLPANOWIDTHPIXELS, "GPano:FullPanoWidthPixels");
        _tagNameMap.put(TAG_XMP_GPANO_INITIALCAMERADOLLY, "GPano:InitialCameraDolly");
        _tagNameMap.put(TAG_XMP_GPANO_INITIALHORIZONTALFOVDEGREES, "GPano:InitialHorizontalFOVDegrees");
        _tagNameMap.put(TAG_XMP_GPANO_INITIALVIEWHEADINGDEGREES, "GPano:InitialViewHeadingDegrees");
        _tagNameMap.put(TAG_XMP_GPANO_INITIALVIEWPITCHDEGREES, "GPano:InitialViewPitchDegrees");
        _tagNameMap.put(TAG_XMP_GPANO_INITIALVIEWROLLDEGREES, "GPano:InitialViewRollDegrees");
        _tagNameMap.put(TAG_XMP_GPANO_LARGESTVALIDINTERIORRECTHEIGHT, "GPano:LargestValidInteriorRectHeight");
        _tagNameMap.put(TAG_XMP_GPANO_LARGESTVALIDINTERIORRECTLEFT, "GPano:LargestValidInteriorRectLeft");
        _tagNameMap.put(TAG_XMP_GPANO_LARGESTVALIDINTERIORRECTTOP, "GPano:LargestValidInteriorRectTop");
        _tagNameMap.put(TAG_XMP_GPANO_LARGESTVALIDINTERIORRECTWIDTH, "GPano:LargestValidInteriorRectWidth");
        _tagNameMap.put(TAG_XMP_GPANO_LASTPHOTODATE, "GPano:LastPhotoDate");
        _tagNameMap.put(TAG_XMP_GPANO_POSEHEADINGDEGREES, "GPano:PoseHeadingDegrees");
        _tagNameMap.put(TAG_XMP_GPANO_POSEPITCHDEGREES, "GPano:PosePitchDegrees");
        _tagNameMap.put(TAG_XMP_GPANO_POSEROLLDEGREES, "GPano:PoseRollDegrees");
        _tagNameMap.put(TAG_XMP_GPANO_PROJECTIONTYPE, "GPano:ProjectionType");
        _tagNameMap.put(TAG_XMP_GPANO_SOURCEPHOTOSCOUNT, "GPano:SourcePhotosCount");
        _tagNameMap.put(TAG_XMP_GPANO_STITCHINGSOFTWARE, "GPano:StitchingSoftware");
        _tagNameMap.put(TAG_XMP_GPANO_USEPANORAMAVIEWER, "GPano:UsePanoramaViewer");
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
