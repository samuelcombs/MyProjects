var currentAreaEl = document.getElementById("current-area").getContext('2d');
var desiredAreaEl = document.getElementById("desired-area").getContext('2d');
var color = randomRGB();

/**
 * This function gets data name for display 
 * This functions is specifically for currentAreaData and desiredAreaData arrays above 
 *      (departmentName is an object property in currentAreaData and desiredAreaData)
 * @param {type} array
 * @returns {Array} array of departmentNames
 */
function getDepartments(array) {
    var nameList = [];
    for (var i = 0; i < array.length; i++) {
        nameList.push(array[i].departmentName);
    }
    return nameList;
}

/**
 * This function gets data numbers for display 
 * This functions is specifically for currentAreaData and desiredAreaData arrays above 
 *      (count is an object property in currentAreaData and desiredAreaData)
 * @param {type} array
 * @returns {Array} array of numbers
 */
function getCount(array) {
    var countList = [];
    for (var i = 0; i < array.length; i++) {
        countList.push(array[i].count);
    }
    return countList;
}

/**
 * This function create array of 50 random rgb colors
 * @returns {Array} array of random colors
 */
function randomRGB() {
    var colors = [];
    for (var i = 0; i < 50; i++) {
        var round = Math.round, random = Math.random, max = 255;
        colors.push('rgba(' + round(random() * max) + ',' + round(random() * max) + ',' + round(random() * max) + ')');
    }
    return colors;
}

/**
 * This function builds chart on the DOM,
 * "elementId" needed to initialize chart in html element
 * "headerText" text above chart
 * "labels" takes array of names
 * "data" takes array of data number related to name
 * "background" takes array of colors 
 * 
 * @param {HTML element} elementId
 * @param {String} headerText
 * @param {Array} nameValues
 * @param {Array} numberValues
 * @param {Array} colors
 * @returns {Chart} 
 */
function buildChart(elementId, headerText, nameValues, numberValues, colors) {
    var chart = new Chart(elementId, {
        responsive: true,
        maintainAspectRatio: true,
        type: "doughnut",
        data: {
            labels: nameValues,
            datasets: [{
                    label: 'Applicants',
                    data: numberValues,
                    backgroundColor: colors
                }]
        },
        options: {
            plugins: {
                labels: {
                    render: 'value',
                    fontSize: 20,
                    fontStyle: 'bold',
                    fontColor: '#fff',
                    fontFamily: '"Lucida Console", Monaco, monospace'
                }
            },
            title: {
                display: true,
                text: headerText,
                fontSize: 26
            },
            legend: {
                position: 'right',
                labels: {
                    fontSize: 16
                }
            }
        }
    });
    return chart;
}