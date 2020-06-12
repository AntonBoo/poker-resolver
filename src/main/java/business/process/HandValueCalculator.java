package business.process;

import java.util.stream.Stream;

import business.HandValue;
import business.Jeu;
import business.process.spec.FlushSpec;
import business.process.spec.FullHouseSpec;
import business.process.spec.HandSpec;
import business.process.spec.HighCardSpec;
import business.process.spec.OnePairSpec;
import business.process.spec.QuadsSpec;
import business.process.spec.StraightFlushSpec;
import business.process.spec.StraightSpec;
import business.process.spec.TripsSpec;
import business.process.spec.TwoPairSpec;

public class HandValueCalculator {
	
	private static final HandSpec HIGH_CARD_SPEC = new HighCardSpec();
	private static final HandSpec ONE_PAIR_SPEC = new OnePairSpec();
	private static final HandSpec TWO_PAIRS_SPEC = new TwoPairSpec();
	private static final HandSpec TRIPS_SPEC = new TripsSpec();
	private static final HandSpec STRAIGHT_SPEC = new StraightSpec();
	private static final HandSpec FLUSH_SPEC = new FlushSpec();
	private static final HandSpec FULL_HOUSE_SPEC = new FullHouseSpec();
	private static final HandSpec QUADS_SPEC = new QuadsSpec();
	private static final HandSpec STRAIGHT_FLUSH_SPEC = new StraightFlushSpec();
	
	public static HandValue process(Jeu jeu) {
		return Stream.of(HIGH_CARD_SPEC,
				ONE_PAIR_SPEC,
				TWO_PAIRS_SPEC,
				TRIPS_SPEC,
				STRAIGHT_SPEC,
				FLUSH_SPEC,
				FULL_HOUSE_SPEC,
				QUADS_SPEC,
				STRAIGHT_FLUSH_SPEC)
		    .filter(spec -> spec.satisfyCondition(jeu))
		    .findFirst()
			.map(handSpec -> new HandValue(handSpec, jeu))
			.orElseThrow(() -> new IllegalStateException("Poker Hand not recognized"));
	}


}
