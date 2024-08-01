package com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveMetadata;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.HumanVascular;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class HumanArteries extends HumanVascular {
    private final List<HumanArteries> connected = ApricotCollectionFactor.arrayList();
    private final Human human;
    private final Random random = new Random();

    public HumanArteries(Human humanBelong) {
        super(humanBelong, 1500, 2000);
        this.human = humanBelong;
    }

    /**
     * <p>Calculate the blood pressure.
     *
     * @author 草
     * @author 草二号机
     *
     * @since 1.0.0
     *
     * @return The blood pressure united by mmHg
     */
    public int bloodPressure(int timed, @Nullable QRSWaveMetadata qrs) {
        // Compliance also known as ’C’ unit is mL.
        double compliance = elasticity() / 1000D;
        // Resistant also known as ’R’ unit is mL.
        double resistant = shrinkage() / 1000D;
        // Input also known as ’CO’ unit is mL.
        double input = (qrs == null ? pumpInputMax() : qrs.calculateCurrentOutput(timed)) / 1000D;
        // Volemia also known as ’V’ unit is mL.
        double volemia = volemia();
        double offset = 1 - Math.exp(- (resistant * compliance));
        double volemiaOffset =  (1 - Math.exp(-resistant));
        double basePressure = volemia * volemiaOffset * (1 - Math.exp(- resistant)) / 3.896695;
        double pumpOffset = 1 - Math.exp(-compliance);
        double pumpPressure = ((input * pumpOffset) * offset) / 1.896695;

        return (int) (
                basePressure + pumpPressure
        );
    }

    @Override
    public double baseVolemiaRate() {
        return 0.1;
    }
}
