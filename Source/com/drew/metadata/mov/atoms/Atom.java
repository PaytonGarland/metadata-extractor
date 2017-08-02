package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;

import java.io.IOException;

public class Atom
{
    public long size;
    public String type;

    public Atom(SequentialReader reader) throws IOException
    {
        this.size = reader.getUInt32();
        this.type = reader.getString(4);
        if (size == 1) {
            size = reader.getInt64();
        } else if (size == 0) {
            size = -1;
        }
    }

    public Atom(Atom atom)
    {
        this.size = atom.size;
        this.type = atom.type;
    }
}
