<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model frontend\models\TChallengeType */

$this->title = 'Create Challenge Type';
$this->params['breadcrumbs'][] = ['label' => 'Challenge Type', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tchallenge-type-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
