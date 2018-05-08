<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model frontend\models\TChallengeType */

$this->title = 'Update Challenge Type: ' . $model->challenge_type_id;
$this->params['breadcrumbs'][] = ['label' => 'Challenge Types', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->challenge_type_id, 'url' => ['view', 'id' => $model->challenge_type_id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="tchallenge-type-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
