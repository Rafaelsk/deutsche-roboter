app = angular.module('Robot', ['ngMaterial'])

app.controller('SheetIds', function($scope, $http) {

    $scope.chat = []

    $http.get('http://localhost:5000/sheetIds').
        then(function(response) {
            $scope.sheetIds = response.data;
        });

    $scope.ask = function() {

        $scope.chat.push({
            "wer": "Mensch",
            "was": $scope.question
        });


        $http.post('http://localhost:5000/answer/' + $scope.selectedSheetId, {'question': $scope.question})
            .then(function(response) {
                $scope.chat.push({
                    "wer": "Roboter",
                    "was": response.data.answer
                });
            });
            $scope.question = '';
    };
});
