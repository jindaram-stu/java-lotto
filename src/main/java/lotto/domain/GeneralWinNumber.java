package lotto.domain;

import constants.ExceptionMessage;
import constants.LottoGame;
import java.util.List;
import java.util.Set;

public class GeneralWinNumber {

    private final List<Integer> generalWinNumbers;

    public GeneralWinNumber(List<Integer> generalWinNumbers) {
        validate(generalWinNumbers);
        this.generalWinNumbers = generalWinNumbers;
    }

    public void validate(List<Integer> generalWinNumbers) {
        if (generalWinNumbers.size() != 6) {
            throw new IllegalArgumentException(ExceptionMessage.MUST_SIX_DIGIT);
        }

        if (isWithinRange(generalWinNumbers)) {
            throw new IllegalArgumentException(ExceptionMessage.WITHIN_RANGE);
        }

        if (Set.copyOf(generalWinNumbers).size() != generalWinNumbers.size()) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATE_NUMBERS);
        }
    }

    public int matchCount(Lotto lotto) {
        return (int) generalWinNumbers.stream()
                .filter(i -> lotto.contains(i))
                .count();
    }

    public boolean contains(BonusNumber bonusNumber) {
        return generalWinNumbers.stream()
                .anyMatch(i -> bonusNumber.isSame(i));
    }

    private boolean isWithinRange(List<Integer> generalWinNumbers) {
        return generalWinNumbers.stream()
                .anyMatch(number -> !(number >= LottoGame.LOTTO_MIN_RANGE && number <= LottoGame.LOTTO_MAX_RANGE));
    }

}
