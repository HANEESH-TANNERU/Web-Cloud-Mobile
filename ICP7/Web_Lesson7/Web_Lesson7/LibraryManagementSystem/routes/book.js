var express = require('express');
var router = express.Router();
var Book = require('../models/Book.js');

router.get('/', function (req, res, next) {
  Book.find(function (err, products) {
    if (err) return next(err);
    res.json(products);
  });
});


router.get('/:id', function (req, res, next) {
  Book.findById(req.params.id, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

router.post('/', function (req, res, next) {
  Book.create(req.body, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

router.put('/:id', function(req, res, next){
  Book.findByIdAndUpdate(req.params.id, req.body, function (err,post){
    if (err) return next(err);
    res.json(post);
  });
});

router.delete('/:id', function(req, res, next){
  Book.findByIdAndDelete(req.params.id, function (err,post){
    if (err) return next(err);
    res.json(post);
  });
});

module.exports = router;
