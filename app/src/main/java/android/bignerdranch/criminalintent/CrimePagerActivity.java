package android.bignerdranch.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity implements CrimeFragment.Callbacks {
    private static final String EXTRA_CRIME_ID = "com.bignerdracnh.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        return new Intent(packageContext, CrimePagerActivity.class).putExtra(EXTRA_CRIME_ID, crimeId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = findViewById(R.id.crime_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Button mFirst = findViewById(R.id.crime_pager_first);
        mFirst.setOnClickListener(v -> mViewPager.setCurrentItem(0));
        Button mLast = findViewById(R.id.crime_pager_last);
        mLast.setOnClickListener(v -> mViewPager.setCurrentItem(mCrimes.size() - 1));
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager,1) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public void startUpdate(@NonNull ViewGroup container) {
                super.startUpdate(container);
                mFirst.setEnabled(mViewPager.getCurrentItem() != 0);
                mLast.setEnabled(mViewPager.getCurrentItem() != (mCrimes.size() - 1));
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        for (int i = 0; i < mCrimes.size(); i++) {
            if(mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }
}
