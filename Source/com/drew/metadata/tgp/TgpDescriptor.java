package com.drew.metadata.tgp;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Payton Garland
 */
public class TgpDescriptor<T extends Directory> extends TagDescriptor<TgpDirectory>
{
    public TgpDescriptor(@NotNull TgpDirectory directory)
    {
        super(directory);
    }

    @Override
    public String getDescription(int tagType)
    {
        switch (tagType) {
            case (TgpDirectory.TAG_MAJOR_BRAND):
                return getMajorBrandDescription(tagType);
            case (TgpDirectory.TAG_COMPATIBLE_BRANDS):
                return getCompatibleBrandsDescription(tagType);
            default:
                return _directory.getString(tagType);
        }
    }

    private String getMajorBrandDescription(int tagType)
    {
        String majorBrandKey = new String(_directory.getByteArray(tagType));
        String majorBrandValue = TgpDictionary.lookup(TgpDirectory.TAG_MAJOR_BRAND, majorBrandKey);
        if (majorBrandValue != null) {
            return majorBrandValue;
        } else {
            return majorBrandKey;
        }
    }

    private String getCompatibleBrandsDescription(int tagType)
    {
        String[] compatibleBrandKeys = _directory.getStringArray(tagType);
        ArrayList<String> compatibleBrandsValues = new ArrayList<String>();
        for (String compatibleBrandsKey : compatibleBrandKeys) {
            String compatibleBrandsValue = TgpDictionary.lookup(TgpDirectory.TAG_MAJOR_BRAND, compatibleBrandsKey);
            if (compatibleBrandsValue != null) {
                compatibleBrandsValues.add(compatibleBrandsValue);
            } else {
                compatibleBrandsValues.add(compatibleBrandsKey);
            }
        }
        return Arrays.toString(compatibleBrandsValues.toArray());
    }
}
