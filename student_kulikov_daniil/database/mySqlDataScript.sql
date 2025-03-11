INSERT IGNORE INTO classifiers (title, description)
VALUES ('RISK_TYPE', 'Risk type classification');

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT classifiers.id, 'TRAVEL_MEDICAL', 'Travel policy medical risk type'
FROM classifiers WHERE classifiers.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT classifiers.id, 'TRAVEL_CANCELLATION', 'Travel policy trip cancellation risk type'
FROM classifiers WHERE classifiers.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT classifiers.id, 'TRAVEL_LOSS_BAGGAGE', 'Travel policy baggage lose risk type'
FROM classifiers WHERE classifiers.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT classifiers.id, 'TRAVEL_THIRD_PARTY_LIABILITY', 'Travel policy third party liability risk type'
FROM classifiers WHERE classifiers.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT classifiers.id, 'TRAVEL_EVACUATION', 'Travel policy evacuation risk type'
FROM classifiers WHERE classifiers.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT classifiers.id, 'TRAVEL_SPORT_ACTIVITIES', 'Travel policy sport activities risk type'
FROM classifiers WHERE classifiers.title = 'RISK_TYPE';

