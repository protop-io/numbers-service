package awesomelabs.numbers;

import io.grpc.stub.StreamObserver;

public class MoneyService extends MoneyServiceGrpc.MoneyServiceImplBase {

    @Override
    public void convert(MoneyOuterClass.ConvertMoneyRequest request,
                        StreamObserver<MoneyOuterClass.Money> responseObserver) {
        MoneyOuterClass.Money originalMoney = request.getOriginal();
        MoneyOuterClass.Currency targetCurrency = request.getTargetCurrency();

        MoneyOuterClass.Money converted = convert(originalMoney, targetCurrency);
        responseObserver.onNext(converted);
        responseObserver.onCompleted();
    }

    private MoneyOuterClass.Money convert(MoneyOuterClass.Money originalMoney,
                                          MoneyOuterClass.Currency targetCurrency) {
        return MoneyOuterClass.Money.newBuilder()
                .setCurrency(targetCurrency)
                .setUnits(originalMoney.getUnits()
                        * getExchangeRate(originalMoney.getCurrency(), targetCurrency))
                .build();
    }

    private double getExchangeRate(MoneyOuterClass.Currency originalCurrency,
                                  MoneyOuterClass.Currency targetCurrency) {
        // Pretend that we're doing the correct thing here!
        return (originalCurrency == targetCurrency)
                ? 1
                : Math.random() * 3;
    }
}
