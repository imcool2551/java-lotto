package lotto.ui;

import lotto.constants.PrizeGrade;
import lotto.domain.LottoResults;
import lotto.domain.PurchaseAction;
import lotto.domain.model.LottoGames;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import static lotto.constants.Message.*;

public class OutputView {

  public void printEntryGames(LottoGames lottoGames) {
    System.out.println(String.format(PURCHASED_N_COUNT, lottoGames.getGameSize()));
    lottoGames.getLottoGames().forEach(lottoGame -> System.out.println(lottoGame.toString()));
    System.out.println(NEW_LINE);
  }

  public void printPrizeStatistics(PurchaseAction purchaseAction, LottoResults lottoResults) {
    System.out.println(PRIZE_STATISTICS_HEADER);
    System.out.println(reportOfStatisticsEachGroup(lottoResults.getGroupedByPrizeGrade()));
    System.out.println(lottoResults.computeRateOfReturn(purchaseAction));
  }

  private String reportOfStatisticsEachGroup(Map<PrizeGrade, Integer> group) {
    return group.entrySet().stream()
        .sorted(Comparator.comparing(entry -> entry.getKey().getPrintOrder()))
        .map(this::formatPrizeExplain)
        .collect(Collectors.joining(NEW_LINE));
  }

  private String formatPrizeExplain(Map.Entry<PrizeGrade, Integer> entry) {
    PrizeGrade prizeGrade = entry.getKey();
    return String.format(PRIZE_STATISTICS_FORMAT, prizeGrade.getMatchCount(), prizeGrade.getPrizeMoney(), entry.getValue());
  }
}
