package android.bignerdranch.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Map <UUID, Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab (Context context) {
        mCrimes = new LinkedHashMap<>();
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
    }

    public Crime getCrime (UUID id) {
        return mCrimes.get(id);
    }

    public Crime addCrime (Crime c) {
        return mCrimes.put(c.getId(),c);
    }

    public Crime deleteCrime (UUID id) { return mCrimes.remove(id); }
}
