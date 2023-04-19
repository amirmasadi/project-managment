let chartDataJson = decodeHtml(chartData);
let chartDataArr= JSON.parse(chartDataJson);
let stages = [];
let counts = [];
for(let i = 0; i < chartDataArr.length; i++){
    stages[i] = chartDataArr[i].stage;
    counts[i] = chartDataArr[i].count;
}
console.log(chartDataArr);
console.log(stages);
console.log(counts);

const ctx = document.getElementById('myChart');

const data = {
  labels: stages,
  datasets: [{
    label: 'Projects Statuses',
    data: counts,
    backgroundColor: [
      'rgb(255, 99, 132)',
      'rgb(54, 162, 235)',
      'rgb(255, 205, 86)'
    ],
    hoverOffset: 4
  }]
};

  const config = {
    type: 'doughnut',
    data: data,
  };

  new Chart(ctx, config);



function decodeHtml(html){
    let textArea = document.createElement("textarea");
    textArea.innerHTML = html;
    return textArea.value;
}