<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use frontend\models\TChallengeType;
use frontend\models\TCheckpoint;
use kartik\select2\Select2;

/* @var $this yii\web\View */
/* @var $model frontend\models\TAchievement */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="tachievement-form">

    <?php $form = ActiveForm::begin(); ?>

    <!--?= $form->field($model, 'challenge_type_id')->textInput() ?-->
    <?= $form->field($model, 'challenge_type_id')->widget(Select2::classname(), [
        'data' => ArrayHelper::map(TChallengeType::find()->all(),'challenge_type_id', 'type_name'),
        'language' => 'en',
        'options' => ['placeholder' => '-Chose Challenge Type-'],
        'pluginOptions' => [
            'allowClear' => true
        ],
    ]);?>

    <!--?= $form->field($model, 'checkpoint_id')->textInput() ?-->
    <?= $form->field($model, 'checkpoint_id')->widget(Select2::classname(), [
        'data' => ArrayHelper::map(TCheckpoint::find()->all(),'checkpoint_id', 'checkpoint_name'),
        'language' => 'en',
        'options' => ['placeholder' => '-Chose Checkpoint-'],
        'pluginOptions' => [
            'allowClear' => true
        ],
    ]);?>

    <?= $form->field($model, 'hadiah')->textInput(['maxlength' => true]) ?>

    <!--?= $form->field($model, 'path_gambar')->textInput(['maxlength' => true]) ?-->
    <?= $form->field($model, 'path_gambar')->fileinput() ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
