package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.majalis.config.MajalisConfigs;
import com.github.cao.awa.majalis.env.human.organ.heart.HumanHeart;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.p.PWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.p.PWaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pipeline.WaveMetadataProducer;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.QRSWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.QRSWaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.q.QWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.q.QWaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.r.RWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.r.RWaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.s.SWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.s.SWaveTrigger;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;
import com.github.cao.awa.majalis.env.tick.Tickable;
import org.apache.logging.log4j.core.config.HttpWatcher;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class HeartbeatTrigger implements Tickable {
    private final PWaveTrigger pWave;
    private final QRSWaveTrigger qrsWave;
    private final QWaveTrigger qWave;
    private final RWaveTrigger rWave;
    private final SWaveTrigger sWave;
    private boolean sinus = true;
    private int times = 0;

    public HeartbeatTrigger(HumanHeart heart, HumanArteries arteries) {
        this.pWave = new PWaveTrigger();
        this.qrsWave = new QRSWaveTrigger(arteries);
        this.qWave = new QWaveTrigger();
        this.rWave = new RWaveTrigger();
        this.sWave = new SWaveTrigger();

        pMetaProcessor(p -> {
            p.troughTime(this.times);
        }).qrsMetaProcessor(qrs -> {
            qrs.troughTime(this.times);
        }).qMetaProcessor(q -> {
            q.troughTime(this.times);
        }).rMetaProcessor(r -> {
            r.troughTime(this.times);
        }).sMetaProcessor(s -> {
            s.troughTime(this.times);
        });

        qrsForward(QRSWaveMetadata::pMeta);

        qForward((q, qrs) -> {
            qrs.qMeta(q);
        });

        rForward((r, qrs) -> {
            qrs.rMeta(r);
        });

        sForward((s, qrs) -> {
            qrs.sMeta(s);
        });
    }

    public int times() {
        return this.times;
    }

    @Override
    public void tick() {
        if (this.sinus) {
            tickSinus();
        }
    }

    public void tickSinus() {
        if (this.times == 0) {
            this.pWave.trigger();
        }

        if (this.times == 20) {
            this.pWave.finish(this.times);
        }

        if (this.times > 20 && this.times < 80) {
            this.qrsWave.forwardFromTrigger(this.pWave);
        } else if (this.times == 80) {
            this.qrsWave.finish(this.times);
        }

        if (this.times >= 30 && this.times < 40) {
            this.qWave.forwardFromTrigger(this.qrsWave);
        } else if (this.times == 40) {
            this.qWave.finish(this.times);
        }

        this.times += MajalisConfigs.expectedTickTime;
    }

    public HeartbeatTrigger sinus(boolean sinus) {
        this.sinus = sinus;
        return this;
    }

    public boolean sinus() {
        return this.sinus;
    }

    public HeartbeatTrigger pSubscribe(Consumer<PWaveMetadata> action) {
        this.pWave.trigger(action);
        return this;
    }

    public HeartbeatTrigger pMetaProcessor(WaveMetadataProducer<PWaveMetadata> action) {
        this.pWave.metadataProcessor(action);
        return this;
    }

    public HeartbeatTrigger qrsSubscribe(Consumer<QRSWaveMetadata> action) {
        this.qrsWave.trigger(action);
        return this;
    }

    public HeartbeatTrigger qrsMetaProcessor(WaveMetadataProducer<QRSWaveMetadata> action) {
        this.qrsWave.metadataProcessor(action);
        return this;
    }

    public HeartbeatTrigger qrsForward(BiConsumer<QRSWaveMetadata, PWaveMetadata> action) {
        this.qrsWave.metadataForwarder(action);
        return this;
    }

    public HeartbeatTrigger qSubscribe(Consumer<QWaveMetadata> action) {
        this.qWave.trigger(action);
        return this;
    }

    public HeartbeatTrigger qMetaProcessor(WaveMetadataProducer<QWaveMetadata> action) {
        this.qWave.metadataProcessor(action);
        return this;
    }

    public HeartbeatTrigger qForward(BiConsumer<QWaveMetadata, QRSWaveMetadata> action) {
        this.qWave.metadataForwarder(action);
        return this;
    }

    public HeartbeatTrigger rSubscribe(Consumer<RWaveMetadata> action) {
        this.rWave.trigger(action);
        return this;
    }

    public HeartbeatTrigger rMetaProcessor(WaveMetadataProducer<RWaveMetadata> action) {
        this.rWave.metadataProcessor(action);
        return this;
    }

    public HeartbeatTrigger rForward(BiConsumer<RWaveMetadata, QRSWaveMetadata> action) {
        this.rWave.metadataForwarder(action);
        return this;
    }

    public HeartbeatTrigger sSubscribe(Consumer<SWaveMetadata> action) {
        this.sWave.trigger(action);
        return this;
    }

    public HeartbeatTrigger sMetaProcessor(WaveMetadataProducer<SWaveMetadata> action) {
        this.sWave.metadataProcessor(action);
        return this;
    }

    public HeartbeatTrigger sForward(BiConsumer<SWaveMetadata, QRSWaveMetadata> action) {
        this.sWave.metadataForwarder(action);
        return this;
    }
}
