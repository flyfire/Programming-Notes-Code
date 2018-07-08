if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'kotlin-javascript'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kotlin-javascript'.");
}
this['kotlin-javascript'] = function (_, Kotlin) {
  'use strict';
  function main(args) {
    document.writeln('Hello World from ' + browerInfoString());
  }
  _.main_kand9s$ = main;
  Kotlin.defineModule('kotlin-javascript', _);
  main([]);
  return _;
}(typeof this['kotlin-javascript'] === 'undefined' ? {} : this['kotlin-javascript'], kotlin);
